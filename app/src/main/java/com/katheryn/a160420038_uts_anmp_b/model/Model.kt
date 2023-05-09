package com.katheryn.a160420038_uts_anmp_b.model

import com.google.gson.annotations.SerializedName

data class Kost(
    val id:String?,
    @SerializedName("kost_name")
    val name:String?,
    @SerializedName("kost_description")
    val description:String?,
    @SerializedName("kost_address")
    val address:String?,
    @SerializedName("kost_rate")
    val rate:String?,
    @SerializedName("kost_price")
    val price:String?,
    @SerializedName("photo_url")
    val photoUrl:String?,
    val photo1:String?,
    val photo2:String?,
    val photo3:String?
)

data class Fasilitas(
    @SerializedName("kost_id")
    val KostID: String?,
    val tipe_kamar: String?,
    val tempat_tidur: String?,
    val pendingin: String?,
    val furniture: String?
)

data class Checkout(
    val kostID: String?,
    val kost_name: String?,
    val totalPrice: String?,
    val photoUrl: String?
)
data class User(
    val id: String?,
    val username: String?,
    val password: String?,
    val phone: String?,
    @SerializedName("photo_url")
    val photoUrl:String?
)