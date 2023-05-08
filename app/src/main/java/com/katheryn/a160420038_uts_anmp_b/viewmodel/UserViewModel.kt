package com.katheryn.a160420038_uts_anmp_b.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.katheryn.a160420038_uts_anmp_b.model.User
import org.json.JSONObject

class UserViewModel(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username: String?, password: String?) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/user.json"
        val stringRequest = object : StringRequest(
            Method.POST, url,
            {
                Log.d("showvolley", it)
                val obj = JSONObject(it)

                if (obj.getString("result") == "success") {
                    val data = obj.getString("data")
                    val result = Gson().fromJson(data, User::class.java)
                } else {
                    Toast.makeText(
                        getApplication(),
                        "Username or password incorrect.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            {
                Log.e("showvolley", it.toString())
            }
        ){

        }
        stringRequest.apply { tag = TAG }

        queue?.add(stringRequest)
    }
    fun fetch() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/user.json"

        val stringRequest = object: StringRequest(
            Method.POST, url,
            {
                Log.d("showvolley", it)
                val obj = JSONObject(it)

                if (obj.getString("result") == "success") {
                    val data = obj.getString("data")
                    val uType = object : TypeToken<ArrayList<User>>() { }.type
                    val result = Gson().fromJson<User>(data, uType)
                    userLD.value = result
                }
            },
            {
                Log.e("showvolley", it.toString())
            }
        ) {
        }
        stringRequest.apply { tag = TAG }

        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}