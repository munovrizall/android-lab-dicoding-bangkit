package com.artonov.dicodingevent.ui.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpcomingDetailViewModelFactory(private val eventId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UpcomingDetailViewModel(eventId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
