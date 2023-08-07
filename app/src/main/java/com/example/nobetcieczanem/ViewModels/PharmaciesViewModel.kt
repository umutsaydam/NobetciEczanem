package com.example.nobetcieczanem.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobetcieczanem.Models.PharmacyModel
import com.example.nobetcieczanem.Repository.PharmacyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PharmaciesViewModel : ViewModel() {
    private val repository = PharmacyRepository()

    val pharmaciesLiveData = MutableLiveData<PharmacyModel>()

    fun refreshDataByCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPharmacyByCity(city)
            pharmaciesLiveData.postValue(response)
        }
    }

    fun refreshDataByCityAndDistrict(district: String, city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPharmacyByCityAndDistrict(district, city)
            pharmaciesLiveData.postValue(response)
        }
    }
}