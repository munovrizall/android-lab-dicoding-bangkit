package com.artonov.talenet.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.repository.LoginRepository
import com.artonov.talenet.data.repository.RegisterRepository
import com.artonov.talenet.ui.home.HomeViewModel
import com.artonov.talenet.ui.login.LoginViewModel
import com.artonov.talenet.ui.register.RegisterViewModel

class ViewModelFactory(
    private val registerRepository: RegisterRepository? = null,
    private val loginRepository: LoginRepository? = null,
    private val userPreference: UserPreference? = null
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(registerRepository!!) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(loginRepository!!, userPreference!!) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(userPreference!!) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}