package com.example.nobetcieczanem.API

import com.example.nobetcieczanem.Models.Cities
import com.example.nobetcieczanem.Models.PharmacyModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("dutyPharmacy")
    suspend fun getPharmacyByCity(@Query("il") city: String): Response<PharmacyModel>

    @GET("dutyPharmacy")
    suspend fun getPharmacyByCityAndDistrict(
        @Query("ilce") district: String,
        @Query("il") city: String
    ): Response<PharmacyModel>

    @GET("districtList")
    suspend fun getCities(): Response<Cities>
}