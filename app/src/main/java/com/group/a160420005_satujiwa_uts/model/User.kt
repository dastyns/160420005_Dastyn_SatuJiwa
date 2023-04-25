package com.group.a160420005_satujiwa_uts.model

import com.google.gson.annotations.SerializedName

data class User (
    val foto:String?,
    val username:String?,
    val nama:String?,
    val alamat:String?,
    @SerializedName("no_handphone")
    val noHandphone:String?,
    val saldo:Int?,
    @SerializedName("total_galangan_dana")
    val totalGalangDana:Int?
)