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
//import com.katheryn.a160420038_uts_anmp_b.model.Fasilitas
import com.katheryn.a160420038_uts_anmp_b.model.Kost
import org.json.JSONObject

class KostListViewModel(application: Application): AndroidViewModel(application) {
    val kostLD = MutableLiveData<ArrayList<Kost>>()
    val kostLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        kostLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/kostUbaya.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<ArrayList<Kost>>() {}.type
                val result = Gson().fromJson<ArrayList<Kost>>(response, sType)
                kostLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("errorvoley", it.toString())
                kostLoadErrorLD.value = false
                loadingLD.value = false
            }).apply {
                tag = "TAG"
        }
        queue?.add(stringRequest)
    }
//    fun fasilitas(id: String){
//        loadingLD.value = true
//        kostLoadErrorLD.value = false
//
//        queue = Volley.newRequestQueue(getApplication())
//        var url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/fasilitas.json"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<ArrayList<Fasilitas>>() { }.type
//                val result = Gson().fromJson<ArrayList<Fasilitas>>(response, sType)
//                fasilitasLD.value = result
//                loadingLD.value = false
//                Log.d("showvolley", response.toString())
//            },
//            {
//                loadingLD.value = false
//                kostLoadErrorLD.value = false
//                Log.d("errorvolley", it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
//    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}