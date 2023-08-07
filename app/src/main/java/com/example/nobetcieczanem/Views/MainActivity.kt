package com.example.nobetcieczanem.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nobetcieczanem.ViewModels.MainActivityViewModel
import com.example.nobetcieczanem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedCity = ""
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.spinnerItems.observe(this) { spinnerItems ->
            val spinnerAdapter =
                ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    spinnerItems.result.map { it.text })
            binding.spinnerCity.adapter = spinnerAdapter
        }
        viewModel.getAllCities()

        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("R/T", parent?.getItemAtPosition(position).toString())
                selectedCity = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedCity = ""
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }

        binding.btnSearch.setOnClickListener {
            val district = binding.txtDistrict.text.toString()
            if (selectedCity != "" && district.isEmpty()) {
                getPharmacies("", selectedCity)
            } else if (selectedCity != "" && district.isNotEmpty()) {
                getPharmacies(district, selectedCity)
            } else {
                Toast.makeText(this, "Please fill the fields.", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun getPharmacies(district: String, city: String){
        val intent = Intent(this, PharmaciesActivity::class.java)
        intent.putExtra("selectedCity", selectedCity)
        intent.putExtra("district", district)
        startActivity(intent)
    }
}