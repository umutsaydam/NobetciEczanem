package com.example.nobetcieczanem.Models

data class PharmacyModel(
    val success: Boolean,
    val result: List<Result>
)

data class Result(
    val name: String,
    val dist: String,
    val address: String,
    val phone: String,
    val loc: String
)


