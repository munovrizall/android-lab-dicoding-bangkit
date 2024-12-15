package com.artonov.talenet.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.repository.StoryRepository
import com.artonov.talenet.data.response.ListStoryItem
import kotlinx.coroutines.launch

class StoryViewModel(
    private val userPreference: UserPreference,
    private val repository: StoryRepository
) : ViewModel() {

    var stories: LiveData<PagingData<ListStoryItem>> =
        repository.getStories().cachedIn(viewModelScope)


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun logout() {
        viewModelScope.launch {
            userPreference.clearToken()
        }
    }
}