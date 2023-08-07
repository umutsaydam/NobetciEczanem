package com.example.nobetcieczanem.Repository

import android.util.Log
import com.example.nobetcieczanem.Models.Cities
import com.example.nobetcieczanem.Models.PharmacyModel
import com.example.nobetcieczanem.RetrofitInstance

class PharmacyRepository {

    suspend fun getPharmacyByCity(city: String): PharmacyModel? {
        val request = RetrofitInstance.apiClient.getPharmacyByCity(city)
        if (request.isSuccessful) {
            return request.body()
        }
        return null
    }

    suspend fun getPharmacyByCityAndDistrict(district: String, city: String): PharmacyModel? {
        val request = RetrofitInstance.apiClient.getPharmacyByCityAndDistrict(district, city)
        if (request.isSuccessful) {
            return request.body()
        }
        return null
    }

    suspend fun getCities(): Cities? {
        val request = RetrofitInstance.apiClient.getCities()
        if (request.isSuccessful){
            return request.body()
        }
        return null
    }
}