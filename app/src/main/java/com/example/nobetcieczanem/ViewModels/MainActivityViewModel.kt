package com.example.nobetcieczanem.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobetcieczanem.Models.Cities
import com.example.nobetcieczanem.Repository.PharmacyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val repository = PharmacyRepository()

    private val spinCitiesLiveData = MutableLiveData<Cities>()
    val spinnerItems: MutableLiveData<Cities> = spinCitiesLiveData

    fun getAllCities(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCities()
            spinCitiesLiveData.postValue(response)
        }

    }
}