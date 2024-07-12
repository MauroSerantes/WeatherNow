package com.myapps.weathernow.data.remote.model.geographical

import com.google.gson.annotations.SerializedName

data class GeographicalResponse(
    val results:List<GeographicalDataResponse>
)

data class GeographicalDataResponse(
    val id:Long,
    val name:String,
    val latitude:Double,
    val longitude:Double,
    @SerializedName("country_code")
    val countryCode:String?=null,
    val country:String?=null,
    val timezone:String?=null,
    @SerializedName("feature_code")
    val featureCode:String,
    val admin1:String?=null,
    val admin2:String?=null,
    val admin3:String?=null,
    val admin4:String?=null
)

