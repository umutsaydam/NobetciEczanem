package com.example.nobetcieczanem.Models

data class Cities(
val success: Boolean,
val result: List<City>,
)

data class City(
    val text: String,
)
