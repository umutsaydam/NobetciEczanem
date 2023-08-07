package com.example.nobetcieczanem.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nobetcieczanem.Adapters.PharmaciesAdapter
import com.example.nobetcieczanem.ViewModels.PharmaciesViewModel
import com.example.nobetcieczanem.databinding.ActivityPharmaciesBinding
import java.lang.Exception

class PharmaciesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPharmaciesBinding
    private val viewModel: PharmaciesViewModel by lazy {
        ViewModelProvider(this)[PharmaciesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPharmaciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        val selectedCity = intent.getStringExtra("selectedCity").toString()
        val district = intent.getStringExtra("district").toString()

        try {
            getPharmacies(district, selectedCity)
        }catch (e: Exception){
            Toast.makeText(this, "Bulunamadı.", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

    }

    private fun getPharmacies(district: String, selectedCity: String) {
        if (selectedCity.isNotEmpty() && district.isNotEmpty()) {
            viewModel.refreshDataByCityAndDistrict(district, selectedCity)
        } else if (selectedCity.isNotEmpty()) {
            viewModel.refreshDataByCity(selectedCity)
        }

        viewModel.pharmaciesLiveData.observe(this) { response ->
            if (!response.success) {
                Log.d("R/T", "NULL GELDı")
                return@observe
            }

            val pharmacyList = response.result
            val pharmacyAdapter = PharmaciesAdapter(this)
            pharmacyAdapter.setPharmacyList(pharmacyList)
            binding.recyclerPharmacy.adapter = pharmacyAdapter

        }
    }
}