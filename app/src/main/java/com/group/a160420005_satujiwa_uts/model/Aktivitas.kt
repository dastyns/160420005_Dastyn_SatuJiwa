package com.group.a160420005_satujiwa_uts.model

import com.google.gson.annotations.SerializedName

data class Aktivitas (
    val id:String?,
    val nama:String?,
    @SerializedName("foto_aktivitas")
    val fotoAktivitas:String?,
    @SerializedName("uang_terkumpul")
    val uangTerkumpul:String?,
    @SerializedName("jumlah_diminta")
    val jumlahDiminta:String?,
    @SerializedName("jumlah_orang")
    val jumlahOrang:String?,
    @SerializedName("hari_tersisa")
    val hariTersisa:String?,
    @SerializedName("penggalang_dana")
    val penggalangDana:String?,
    val cerita:String?,
    @SerializedName("foto_penggalang")
    val fotoPenggalang:String?
)