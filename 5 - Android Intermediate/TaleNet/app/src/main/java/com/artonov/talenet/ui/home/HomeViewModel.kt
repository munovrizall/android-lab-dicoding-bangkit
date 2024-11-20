package com.artonov.talenet.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artonov.talenet.data.preference.UserPreference
import kotlinx.coroutines.launch

class HomeViewModel(private val userPreference: UserPreference) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            userPreference.clearToken()
        }
    }
}