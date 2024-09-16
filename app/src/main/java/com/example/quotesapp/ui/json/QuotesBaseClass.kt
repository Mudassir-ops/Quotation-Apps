package com.example.quotesapp.ui.json

import com.google.gson.annotations.SerializedName

data class QuotesBaseClass(
    @SerializedName("quotes_data") var quotesData: QuotesData? = null,

    )
