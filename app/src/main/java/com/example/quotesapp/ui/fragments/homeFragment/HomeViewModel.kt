package com.example.quotesapp.ui.fragments.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.ui.json.QuotesData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val ioDispatcher = IO

    private val _quotesData = MutableStateFlow<QuotesData?>(null)
    val quotesData: StateFlow<QuotesData?> get() = _quotesData

    init {
        fetchQuotes(ioDispatcher)
    }

    private fun fetchQuotes(coroutineDispatcher: CoroutineDispatcher?) {
        viewModelScope.launch(coroutineDispatcher ?: return) {
            val data = homeRepository.fetchQuotesData(coroutineDispatcher)
            data?.let { _quotesData.value = (it) }


        }
    }

}