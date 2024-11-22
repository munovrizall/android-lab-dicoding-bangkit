package com.artonov.talenet.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.artonov.talenet.data.ViewModelFactory
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.repository.LoginRepository
import com.artonov.talenet.data.repository.RegisterRepository
import com.artonov.talenet.data.repository.StoryAddRepository
import com.artonov.talenet.data.repository.StoryDetailRepository
import com.artonov.talenet.data.repository.StoryRepository
import com.artonov.talenet.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injector {

    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

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
        val userPreference = provideUserPreference(context)
        return ViewModelFactory(
            registerRepository = registerRepository,
            userPreference = userPreference
        )
    }

    fun provideLoginViewModelFactory(context: Context): ViewModelFactory {
        val loginRepository = provideLoginRepository()
        val userPreference = provideUserPreference(context)
        return ViewModelFactory(loginRepository = loginRepository, userPreference = userPreference)
    }

    private fun provideStoryRepository(context: Context): StoryRepository {
        val userPreference = provideUserPreference(context)
        val user = runBlocking { userPreference.getUser().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return StoryRepository.getInstance(apiService, userPreference)
    }

    fun provideStoryViewModelFactory(context: Context): ViewModelFactory {
        val storyRepository = provideStoryRepository(context)
        val userPreference = provideUserPreference(context)
        return ViewModelFactory(storyRepository = storyRepository, userPreference = userPreference)
    }

    private fun provideStoryDetailRepository(context: Context): StoryDetailRepository {
        val userPreference = provideUserPreference(context)
        val user = runBlocking { userPreference.getUser().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return StoryDetailRepository.getInstance(apiService, userPreference)
    }

    fun provideStoryDetailViewModelFactory(context: Context): ViewModelFactory {
        val storyDetailRepository = provideStoryDetailRepository(context)
        val userPreference = provideUserPreference(context)
        return ViewModelFactory(storyDetailRepository = storyDetailRepository, userPreference = userPreference)
    }

    private fun provideStoryAddRepository(context: Context): StoryAddRepository {
        val userPreference = provideUserPreference(context)
        val user = runBlocking { userPreference.getUser().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return StoryAddRepository.getInstance(apiService, userPreference)
    }

    fun provideStoryAddViewModelFactory(context: Context): ViewModelFactory {
        val storyAddRepository = provideStoryAddRepository(context)
        val userPreference = provideUserPreference(context)
        return ViewModelFactory(storyAddRepository = storyAddRepository, userPreference = userPreference)
    }
}