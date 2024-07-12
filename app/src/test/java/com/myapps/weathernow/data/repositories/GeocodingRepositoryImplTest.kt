package com.myapps.weathernow.data.repositories

import com.myapps.weathernow.MainCoroutineRule
import com.myapps.weathernow.data.local.source.GeocodingLocalSource
import com.myapps.weathernow.data.remote.source.geographical.GeocodingRemoteSource
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GeocodingRepositoryImplTest{
    private lateinit var geocodingRepository:GeocodingRepositoryImpl

    private val geocodingRemoteSourceMock = mockk<GeocodingRemoteSource>()
    private val geocodingLocalSourceMock = mockk<GeocodingLocalSource>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        geocodingRepository = GeocodingRepositoryImpl(
            geocodingRemoteSourceMock,
            geocodingLocalSourceMock,
            mainCoroutineRule.testDispatcher
        )
    }


    @Test
    fun`when fetch geographical data from remote source and its status is success should return data status success with info`()=
        runTest {


        }
    @Test
    fun `when fetch geographical data from remote source and its status is error should return data status error with an optional message`() =
        runTest {

        }


}