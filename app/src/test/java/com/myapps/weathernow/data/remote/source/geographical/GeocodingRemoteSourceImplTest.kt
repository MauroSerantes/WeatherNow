package com.myapps.weathernow.data.remote.source.geographical

import com.myapps.weathernow.core.mappers.toDto
import com.myapps.weathernow.data.model.geographical.GeographicalDataDto
import com.myapps.weathernow.data.remote.model.geographical.GeographicalDataResponse
import com.myapps.weathernow.data.remote.model.geographical.GeographicalResponse
import com.myapps.weathernow.data.remote.service.geocoding.GeocodingService
import com.myapps.weathernow.utils.DataStatus
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Response

class GeocodingRemoteSourceImplTest {

    private val geocodingService = mockk<GeocodingService>(relaxed = true)

    private val remoteSource = GeocodingRemoteSourceImpl(geocodingService)

    private val responseMock = mockk<Response<GeographicalResponse>>(relaxed = true)


    @Test
    fun `given a successful response should return a Data Status Success with the response`() =
        runTest {
            val remoteResponse = GeographicalResponse(
                listOf(
                    GeographicalDataResponse(
                        id = 123, "libia", 44.3, 22.4, null, "albania", null, "PLC"
                    ),
                    GeographicalDataResponse(
                        id = 123,
                        name = "libia",
                        latitude = 44.3,
                        longitude = 22.4,
                        country = "albania",
                        featureCode = "PLC"
                    ),
                    GeographicalDataResponse(
                        id = 123,
                        name = "libia",
                        latitude = 44.3,
                        longitude = 22.4,
                        country = "albania",
                        featureCode = "PLC"
                    ),
                    GeographicalDataResponse(
                        id = 123,
                        name = "libia",
                        latitude = 44.3,
                        longitude = 22.4,
                        country = "albania",
                        featureCode = "PLC"
                    ),
                    GeographicalDataResponse(
                        id = 123,
                        name = "libia",
                        latitude = 44.3,
                        longitude = 22.4,
                        country = "albania",
                        featureCode = "PLC"
                    )
                )
            )

            coEvery { geocodingService.searchLocation(any()) } returns responseMock
            every { responseMock.isSuccessful } returns true
            every { responseMock.body() } returns remoteResponse

            assertEquals(
                remoteSource.searchLocation("mar del plata"),
                DataStatus.Success(data = remoteResponse.results.map { it.toDto() })
            )

            coVerify { geocodingService.searchLocation(any()) }
            verify { responseMock.isSuccessful }
            verify { responseMock.body() }
        }


    @Test
    fun `given an unsuccessful response should return a Data status Error with an optional message`() =
        runTest {
            val message = "Something fails"

            coEvery { geocodingService.searchLocation(any()) } returns responseMock
            every { responseMock.isSuccessful } returns false
            every { responseMock.message() } returns message

            assertEquals(
                remoteSource.searchLocation("mar del plata"),
                DataStatus.Error<List<GeographicalDataDto>>(errorMessage = message)
            )

            coVerify { geocodingService.searchLocation(any()) }
            verify { responseMock.isSuccessful }
            verify { responseMock.message() }
        }

    @Test
    fun `given a successful response with a null body should return a Data Status Success with an empty list`() =
        runTest {
            coEvery{ geocodingService.searchLocation(any()) } returns responseMock
            every { responseMock.isSuccessful } returns true
            every { responseMock.body() } returns null

            assertEquals(
                remoteSource.searchLocation("mar del plata"),
                DataStatus.Success<List<GeographicalDataDto>>(emptyList())
            )

            coVerify { geocodingService.searchLocation(any()) }
            verify { responseMock.isSuccessful }
            verify { responseMock.body()}
        }


}