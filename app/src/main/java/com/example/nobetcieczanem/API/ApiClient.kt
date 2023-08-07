package com.example.nobetcieczanem.API

import android.util.Log
import com.example.nobetcieczanem.Models.Cities
import com.example.nobetcieczanem.Models.PharmacyModel
import retrofit2.Response

class ApiClient(private val apiService: ApiService) {

    suspend fun getPharmacyByCity(city: String): Response<PharmacyModel> {
        return apiService.getPharmacyByCity(city)
    }

    suspend fun getPharmacyByCityAndDistrict(
        district: String,
        city: String
    ): Response<PharmacyModel>  {
        return apiService.getPharmacyByCityAndDistrict(district, city)
    }

    suspend fun getCities(): Response<Cities>{
        return apiService.getCities()
    }
}