package com.example.quotesapp.ui.fragments.quotesFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {
}