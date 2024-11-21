package com.artonov.talenet.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.repository.LoginRepository
import com.artonov.talenet.data.repository.RegisterRepository
import com.artonov.talenet.data.repository.StoryDetailRepository
import com.artonov.talenet.data.repository.StoryRepository
import com.artonov.talenet.ui.story.StoryViewModel
import com.artonov.talenet.ui.login.LoginViewModel
import com.artonov.talenet.ui.register.RegisterViewModel
import com.artonov.talenet.ui.story_detail.StoryDetailViewModel

class ViewModelFactory(
    private val registerRepository: RegisterRepository? = null,
    private val loginRepository: LoginRepository? = null,
    private val storyRepository: StoryRepository? = null,
    private val storyDetailRepository: StoryDetailRepository? = null,
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
            modelClass.isAssignableFrom(StoryViewModel::class.java) -> {
                StoryViewModel(userPreference!!, storyRepository!!) as T
            }
            modelClass.isAssignableFrom(StoryDetailViewModel::class.java) -> {
                StoryDetailViewModel(storyDetailRepository!!) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}