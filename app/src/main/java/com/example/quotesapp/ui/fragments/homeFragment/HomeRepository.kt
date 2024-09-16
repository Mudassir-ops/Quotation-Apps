package com.example.quotesapp.ui.fragments.homeFragment

import android.content.Context
import android.util.Log
import com.example.quotesapp.ui.json.QuotesBaseClass
import com.example.quotesapp.ui.json.QuotesData
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun fetchQuotesData(coroutineDispatcher: CoroutineDispatcher?): QuotesData? {
        return withContext(coroutineDispatcher ?: return null) {
            try {
                val inputStream = context.assets.open("quotes.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                Log.e("HomeRepository", "fetchQuotesData: jsonString $jsonString")
                val gson = Gson()
                val quotesData = gson.fromJson(jsonString, QuotesBaseClass::class.java)
                Log.e("HomeRepository", "fetchQuotesData: quotes $quotesData")
                quotesData.quotesData
            } catch (e: Exception) {
                e.printStackTrace()

                Log.e("HomeRepository", "fetchQuotesData: quotes ${e.printStackTrace()}")
                null
            }
        }
    }


}