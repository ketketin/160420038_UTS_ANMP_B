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

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val kostLD = MutableLiveData<Kost>()
    val kostLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id: String) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/kost.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {

                val sType = object : TypeToken<ArrayList<Kost>>() { }.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                for(kostList in result){
                    if(kostList.id == id){
                        kostLD.value = kostList
                    }
                }
                loadingLD.value = false

                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                kostLoadErrorLD.value = false
                loadingLD.value = false
            }).apply {
//            stringRequest.tag = TAG
                tag = "TAG"
        }
        queue?.add(stringRequest)
    }
}