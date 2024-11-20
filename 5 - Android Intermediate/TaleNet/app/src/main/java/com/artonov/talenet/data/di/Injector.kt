package com.artonov.talenet.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.artonov.talenet.data.ViewModelFactory
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.repository.LoginRepository
import com.artonov.talenet.data.repository.RegisterRepository
import com.artonov.talenet.data.repository.StoryRepository
import com.artonov.talenet.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injector {

    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    fun provideStoryRepository(context: Context): StoryRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getUser().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return StoryRepository.getInstance(apiService, pref)
    }

    private fun provideRegisterRepository(): RegisterRepository {
        val apiService = ApiConfig.getApiService()
        return RegisterRepository(apiService)
    }

    private fun provideLoginRepository(): LoginRepository {
        val apiService = ApiConfig.getApiService()
        return LoginRepository(apiService)
    }

    private fun provideUserPreference(context: Context): UserPreference {
        val dataStore = context.dataStore
        return UserPreference.getInstance(dataStore)
    }

    fun provideRegisterViewModelFactory(context: Context): ViewModelFactory {
        val registerRepository = provideRegisterRepository()
        val loginRepository = provideLoginRepository()
        val userPreference = provideUserPreference(context)
        return ViewModelFactory(registerRepository, loginRepository, userPreference)
    }

    fun provideLoginViewModelFactory(context: Context): ViewModelFactory {
        val registerRepository = provideRegisterRepository()
        val loginRepository = provideLoginRepository()
        val userPreference = provideUserPreference(context)
        return ViewModelFactory(loginRepository = loginRepository, userPreference = userPreference)
    }

    fun provideHomeViewModelFactory(context: Context): ViewModelFactory {
        val userPreference = provideUserPreference(context)
        return ViewModelFactory(userPreference = userPreference)
    }
}