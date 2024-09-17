package com.example.quotesapp.ui.fragments.homeFragment

import android.content.Context
import com.example.quotesapp.ui.json.QuotesBaseClass
import com.example.quotesapp.ui.json.QuotesData
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun fetchQuotesData(coroutineDispatcher: CoroutineDispatcher?): QuotesData? {
        return withContext(coroutineDispatcher ?: return null) {
            try {
                val inputStream = context.assets.open("quotes.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val gson = Gson()
                val quotesData = gson.fromJson(jsonString, QuotesBaseClass::class.java)
                quotesData.quotesData
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }


}