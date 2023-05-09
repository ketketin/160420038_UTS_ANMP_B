package com.katheryn.a160420038_uts_anmp_b.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.katheryn.a160420038_uts_anmp_b.model.Kost

class CheckoutViewModel(application: Application): AndroidViewModel(application) {
    val myKostLD = MutableLiveData<ArrayList<Kost>>()
    val checkoutLD = MutableLiveData<Kost>()
    val checkoutLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        checkoutLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/checkout.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<ArrayList<Kost>>() {}.type
                val result = Gson().fromJson<ArrayList<Kost>>(response, sType)
                myKostLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("errorvoley", it.toString())
                checkoutLoadErrorLD.value = false
                loadingLD.value = false
            }).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    fun fetch(id: String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/checkout.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response->
                val sType = object : TypeToken<ArrayList<Kost>>() { }.type
                val result = Gson().fromJson<ArrayList<Kost>>(response, sType)
                for(i in result){
                    if(i.id == id){
                        checkoutLD.value = i
                    }
                }
                Log.d("showvoley", response)
            },
            {
                Log.d("showvoley", it.toString())
            }).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}