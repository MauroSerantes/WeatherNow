package com.myapps.weathernow.domain.utils


fun getErrorDescription(stringError:String):String{
    return when(stringError){
        Errors.NO_GPS_ACTIVATED ->{
            ErrorsMessages.NO_GPS
        }
        Errors.NO_PERMISSION_GRANTED ->{
            ErrorsMessages.NO_PERMISSION
        }
        Errors.NO_INTERNET_CONNECTION ->{
            ErrorsMessages.NO_INTERNET
        }
        else->{
            "Unknow Error"
        }
    }
}