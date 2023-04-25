package com.group.a160420005_satujiwa_uts.viewmodel

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
import com.group.a160420005_satujiwa_uts.model.Tentang
import com.group.a160420005_satujiwa_uts.model.User

class ListViewUser(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<ArrayList<User>>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch() {
        loadingLD.value =true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/adv/akun.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<User>>() { }.type
                val result = Gson().fromJson<ArrayList<User>>(it, sType)
                userLD.value = result
                loadingLD.value =false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}