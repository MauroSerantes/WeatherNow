package com.myapps.weathernow.utils

enum class Status{
    SUCCESS,
    ERROR
}
sealed class DataStatus<out T >(val status: Status, val data: T?, val errorMessage: String?){
    class Success<T>(data: T?) : DataStatus<T>(Status.SUCCESS,data,null)
    class Error<T>(errorMessage:String?) : DataStatus<T>(Status.ERROR,null,errorMessage)
}


