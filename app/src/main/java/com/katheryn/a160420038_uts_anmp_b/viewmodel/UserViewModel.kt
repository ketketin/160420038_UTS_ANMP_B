package com.katheryn.a160420038_uts_anmp_b.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.katheryn.a160420038_uts_anmp_b.Global
import com.katheryn.a160420038_uts_anmp_b.model.User
import org.json.JSONObject

class UserViewModel(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/user.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<ArrayList<User>>() { }.type
                val result = Gson().fromJson<ArrayList<User>>(response, sType)
                for(i in result){
                    if(i.id == Global.userID){
                        userLD.value = i
                    }
                }
//                userLD.value = result
                Log.d("showvolley", response)
            },
            {
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }

        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}