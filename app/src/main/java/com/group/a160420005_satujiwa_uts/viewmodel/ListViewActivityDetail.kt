package com.group.a160420005_satujiwa_uts.viewmodel

import android.app.Activity
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
import com.group.a160420005_satujiwa_uts.model.Aktivitas
import com.group.a160420005_satujiwa_uts.model.User

class ListViewActivityDetail(application: Application): AndroidViewModel(application) {
    val activityDetailLD = MutableLiveData<Aktivitas>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(idActivity:String) {
        loadingLD.value =true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/adv/activity.php?id="+idActivity
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Aktivitas>(it, Aktivitas::class.java)
                activityDetailLD.value = result

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