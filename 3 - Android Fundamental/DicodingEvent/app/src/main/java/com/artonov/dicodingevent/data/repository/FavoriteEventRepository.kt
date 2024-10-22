package com.artonov.dicodingevent.data.repository

import com.artonov.dicodingevent.data.database.FavoriteEvent
import android.app.Application
import androidx.lifecycle.LiveData
import com.artonov.dicodingevent.data.database.FavoriteEventDao
import com.artonov.dicodingevent.data.database.FavoriteEventDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteEventRepository(application: Application) {
    private val mFavoriteEventDao: FavoriteEventDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteEventDatabase.getDatabase(application)
        mFavoriteEventDao = db.favoriteEventDao()
    }

    fun getAllFavoriteEvents(): LiveData<List<FavoriteEvent>> =
        mFavoriteEventDao.getAllFavoriteEvents()

    fun insert(favoriteEvent: FavoriteEvent) {
        executorService.execute { mFavoriteEventDao.insert(favoriteEvent) }
    }

    fun delete(favoriteEvent: FavoriteEvent) {
        executorService.execute { mFavoriteEventDao.delete(favoriteEvent) }
    }
}