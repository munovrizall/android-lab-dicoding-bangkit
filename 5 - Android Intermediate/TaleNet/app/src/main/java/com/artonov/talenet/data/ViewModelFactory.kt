package com.artonov.talenet.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artonov.talenet.data.repository.RegisterRepository
import com.artonov.talenet.ui.register.RegisterViewModel

class ViewModelFactory(private val repository: RegisterRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
