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
import com.group.a160420005_satujiwa_uts.model.GalangSaya

class ListViewGalangDana(application: Application): AndroidViewModel(application) {
    val galangDanaLD = MutableLiveData<ArrayList<GalangSaya>>()
    val galangDanaLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value =true
        galangDanaLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/adv/galangdanasaya.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<GalangSaya>>(  ) { }.type
                val result = Gson().fromJson<ArrayList<GalangSaya>>(it, sType)
                galangDanaLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                galangDanaLoadErrorLD.value = true
                loadingLD.value = false
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