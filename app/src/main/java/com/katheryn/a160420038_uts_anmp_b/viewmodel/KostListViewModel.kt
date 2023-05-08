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
        val url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/kost.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                loadingLD.value = false
                Log.d("showvoley", it)

                val sType = object : TypeToken<ArrayList<Kost>>() {}.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                kostLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                kostLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

//    fun bookmark(){
//        queue = Volley.newRequestQueue(getApplication())
//        val url = ""
//        val stringRequest = object : StringRequest(
//            Method.POST, url,
//            {
//                Log.d("showvolley", it)
//                val obj = JSONObject(it)
//
//                if(obj.getString("result") == "success"){
//                    val data = obj.getS
//                }
//            }
//        )
//    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}