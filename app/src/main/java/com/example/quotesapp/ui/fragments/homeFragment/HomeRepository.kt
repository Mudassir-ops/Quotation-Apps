package com.example.quotesapp.ui.fragments.homeFragment

import QuotesData
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun fetchQuotesData(): QuotesData? {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream = context.assets.open("quotes.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                Log.e("HomeRepository", "fetchQuotesData: jsonString $jsonString")

                val gson = Gson()
                val quotesData = gson.fromJson(jsonString, QuotesData::class.java)
                Log.e("HomeRepository", "fetchQuotesData: quotes $quotesData")

                quotesData
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }


}