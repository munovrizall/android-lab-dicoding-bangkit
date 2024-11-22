package com.artonov.talenet.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.repository.StoryRepository
import com.artonov.talenet.data.response.ListStoryItem
import kotlinx.coroutines.launch

class StoryViewModel(
    private val userPreference: UserPreference,
    private val repository: StoryRepository
) : ViewModel() {

    private val _listStory = MutableLiveData<List<ListStoryItem>>()
    val listStory: LiveData<List<ListStoryItem>> = _listStory

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun showStories() {
        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            try {
                val response = repository.getStories()
                _listStory.value = response.listStory
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreference.clearToken()
        }
    }
}