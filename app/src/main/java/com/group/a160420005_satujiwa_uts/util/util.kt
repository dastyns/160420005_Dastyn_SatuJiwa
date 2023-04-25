package com.group.a160420005_satujiwa_uts.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.group.a160420005_satujiwa_uts.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?, progressBar:ProgressBar) {
    Picasso.get()
        .load(url).resize(400, 400).centerCrop()
        .error(R.drawable.ic_baseline_error_outline_24)
        .into(this, object:Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
        })
}

//fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        val channelId = "${context.packageName}-$name"
//        val channel = NotificationChannel(channelId, name, importance)
//        channel.description = description
//        channel.setShowBadge(showBadge)
//
//        val notificationManager = context.getSystemService(NotificationManager::class.java)
//        notificationManager.createNotificationChannel(channel)
//
//    }
//}