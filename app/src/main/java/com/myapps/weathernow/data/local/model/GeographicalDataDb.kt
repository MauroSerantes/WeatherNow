package com.myapps.weathernow.data.local.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myapps.weathernow.data.local.model.converters.LocalDateTimeConverter
import com.myapps.weathernow.data.local.service.GeographicalDataCacheDao
import com.myapps.weathernow.data.local.service.GeographicalDataDao
import com.myapps.weathernow.data.local.service.WeatherGeographicalDataDao

@Database(
    entities = [GeographicalData::class, GeographicalDataCache::class,WeatherGeographicalData::class],
    version = 9,
    exportSchema = true
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class GeographicalDataDb : RoomDatabase() {
    abstract fun geographicalDataDao(): GeographicalDataDao
    abstract fun geographicalDataCacheDao(): GeographicalDataCacheDao
    abstract fun weatherGeographicalDataDao():WeatherGeographicalDataDao
}