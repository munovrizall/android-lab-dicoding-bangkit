package com.dicoding.asclepius.view.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.data.remote.retrofit.ApiService
import kotlinx.coroutines.launch

class ArticleViewModel(private val apiService: ApiService) : ViewModel() {
    private val apiKey = BuildConfig.API_KEY

    private val _listArticle = MutableLiveData<List<ArticlesItem>>()
    val listArticle: LiveData<List<ArticlesItem>> = _listArticle

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        viewModelScope.launch {
            showArticles()
        }
    }

    private suspend fun showArticles(): List<ArticlesItem> {
        _isLoading.value = true
        _errorMessage.value = null

        try {
            val response = apiService.getArticle(QUERY, CATEGORY, LANGUAGE, apiKey)
            if (response.status == "ok") {
            _isLoading.value = false
                return response.articles ?: emptyList()
            } else {
                    _errorMessage.value = "Data tidak ditemukan"
                Log.e("NewsViewModel", "Error fetching articles")
            }

        } catch (e: Exception) {
            _isLoading.value = false
            _errorMessage.value = "Gagal memuat data: ${e.message}"
            Log.e(TAG, "onFailure: ${e.message}")
        }
        return emptyList()
    }

    companion object {
        private const val TAG = "UpcomingViewModel"
        private const val QUERY = "cancer"
        private const val CATEGORY = "health"
        private const val LANGUAGE = "en"
    }
}