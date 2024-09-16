package com.example.quotesapp.ui.json

import com.google.gson.annotations.SerializedName


data class Categories(

    @SerializedName("category_name") var categoryName: String? = null,
    @SerializedName("quotes") var quotes: ArrayList<Quotes>? = null,
)