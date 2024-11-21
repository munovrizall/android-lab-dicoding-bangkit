package com.artonov.talenet.ui.story_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.repository.StoryDetailRepository
import com.artonov.talenet.data.repository.StoryRepository
import com.artonov.talenet.data.response.ListStoryItem
import com.artonov.talenet.data.response.Story
import com.artonov.talenet.data.response.StoryDetailResponse
import com.artonov.talenet.data.response.StoryResponse
import kotlinx.coroutines.launch

class StoryDetailViewModel(
    private val repository: StoryDetailRepository
) : ViewModel() {

    private val _story = MutableLiveData<Story>()
    val story: LiveData<Story> = _story

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun showDetailStory(id: String) {
        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            try {
                val response = repository.getStoryDetail(id)
                _story.value = response.story!!
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}