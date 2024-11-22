package com.fiap.brenotosi_rm93435.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DicasViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DicasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DicasViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}