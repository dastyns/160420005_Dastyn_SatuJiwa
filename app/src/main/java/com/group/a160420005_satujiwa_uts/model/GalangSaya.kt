package com.group.a160420005_satujiwa_uts.model

import com.google.gson.annotations.SerializedName

data class GalangSaya (
    @SerializedName("nama_aktivitas")
    val namaAktivitas:String?,
    val nominal:Int?,
    val foto:String?
)