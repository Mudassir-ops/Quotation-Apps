package com.example.quotesapp.ui.fragments.homeFragment

import QuotesData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _quotesData = MutableLiveData<QuotesData>()
    val quotesData: LiveData<QuotesData> get() = _quotesData

    init {
        fetchQuotes()
    }

    private fun fetchQuotes() {
        viewModelScope.launch {
            val data = homeRepository.fetchQuotesData()
            data?.let { _quotesData.postValue(it) }
        }
    }
}