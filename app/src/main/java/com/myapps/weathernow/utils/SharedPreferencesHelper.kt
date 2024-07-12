package com.myapps.weathernow.utils

import android.content.Context

object SharedPreferencesHelper{
    private const val SHAREDPREF_KEY = "myPref_key"

    fun saveBooleanData(context: Context, key:String, data:Boolean){
        context.getSharedPreferences(SHAREDPREF_KEY, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key,data)
            .apply()
    }

    fun getBooleanData(context: Context, key: String):Boolean{
        return context.getSharedPreferences(SHAREDPREF_KEY, Context.MODE_PRIVATE)
            .getBoolean(key,false)
    }

    fun clearData(context: Context){
        context.getSharedPreferences(SHAREDPREF_KEY, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }
}