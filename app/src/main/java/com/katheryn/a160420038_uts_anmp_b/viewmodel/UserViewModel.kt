package com.katheryn.a160420038_uts_anmp_b.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.katheryn.a160420038_uts_anmp_b.model.User
import com.katheryn.a160420038_uts_anmp_b.view.MainActivity
import org.json.JSONObject

class UserViewModel(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()
    val userLoadErrorLD = MutableLiveData<User>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username: String?, password: String?){
//        queue = Volley.newRequestQueue(this)
        var url = "https://raw.githubusercontent.com/ketketin/json_uts_anmp/main/user.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<User>() { }.type
                val result = Gson().fromJson<User>(response, sType)
                if(result.username == username){
                    if(result.password == password){
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                        finish()
                    } else {
//                        Toast.makeText(this, "Your password is incorrect", Toast.LENGTH_SHORT).show()
                    }
                } else {
//                    Toast.makeText(this, "Your username is incorrect", Toast.LENGTH_SHORT).show()
                }
//                    }
                //restaurantDetailLiveData.value = result
                Log.d("showvolley", response.toString())
            },
            {
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }

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
                    val result = Gson().fromJson<ArrayList<User>>(data, uType)
                    userLoadErrorLD.value = result[0]
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