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

class BookmarkViewModel(application: Application): AndroidViewModel(application) {
    val bookmarLD = MutableLiveData<ArrayList<Kost>>()
    val bookmarkLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        bookmarkLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/bookmark.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Kost>>() { }.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                bookmarLD.value = result
                loadingLD.value = false
                Log.d("showvolley", it.toString())
            },
            {
                loadingLD.value = false
                bookmarkLoadErrorLD.value = true
            }
        ).apply {
            tag = "TAG"
        }

        queue?.add(stringRequest)
    }
}