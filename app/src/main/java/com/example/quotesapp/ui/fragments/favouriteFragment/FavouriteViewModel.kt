package com.example.quotesapp.ui.fragments.favouriteFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.quotesapp.data.QuotesDao
import com.example.quotesapp.data.QuotesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val quotesDao: QuotesDao
) : ViewModel() {
    val favouriteQuotes: LiveData<List<QuotesEntity>> =
        quotesDao.getAllFavouriteQuotesFlow().asLiveData()


}