package com.myapps.weathernow.utils

enum class Status{
    SUCCESS,
    ERROR
}
sealed class DataStatus<out T >(val status: Status, open val data:T?, open val errorMessage: String?){
    data class Success<T>(override val data: T?) : DataStatus<T>(Status.SUCCESS,data,errorMessage = null)
    data class Error<T>(override val errorMessage:String?) : DataStatus<T>(Status.ERROR, data = null,errorMessage)
}


