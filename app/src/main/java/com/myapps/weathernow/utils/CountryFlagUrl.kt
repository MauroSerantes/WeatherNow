package com.myapps.weathernow.utils


object CountryFlagUrl {
    fun countryFlag(countryCode: String):String{
       return "https://countryflagsapi.netlify.app/flag/$countryCode.svg"
    }
}
