package com.artonov.talenet.data.di

import android.content.Context
import com.artonov.talenet.data.repository.RegisterRepository
import com.artonov.talenet.data.retrofit.ApiConfig
import com.artonov.talenet.data.ViewModelFactory

object Injector {

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val apiService = ApiConfig.getApiService()
        val repository = RegisterRepository(apiService)
        return ViewModelFactory(repository)
    }

}