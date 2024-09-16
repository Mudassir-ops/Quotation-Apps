package com.example.quotesapp.ui.json

import com.google.gson.annotations.SerializedName


data class QuotesData(
    @SerializedName("categories") var categories: ArrayList<Categories> = arrayListOf()
)