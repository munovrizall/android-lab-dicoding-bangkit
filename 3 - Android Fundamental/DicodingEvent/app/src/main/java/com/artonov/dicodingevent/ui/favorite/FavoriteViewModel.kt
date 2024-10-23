package com.artonov.dicodingevent.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.artonov.dicodingevent.data.database.FavoriteEvent
import com.artonov.dicodingevent.data.repository.FavoriteEventRepository

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mFavoriteEventRepository: FavoriteEventRepository = FavoriteEventRepository(application)

    fun getFavoriteEvents(): LiveData<List<FavoriteEvent>> {
        return mFavoriteEventRepository.getAllFavoriteEvents()
    }
}