package com.artonov.core.data.source.local

import com.artonov.core.data.source.local.entity.TourismEntity
import com.artonov.core.data.source.local.room.TourismDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val tourismDao: TourismDao) {

//    hapus kode berikut
//    companion object {
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(tourismDao: TourismDao): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(tourismDao)
//            }
//    }

    fun getAllTourism(): Flow<List<TourismEntity>> = tourismDao.getAllTourism()

    fun getFavoriteTourism(): Flow<List<TourismEntity>> = tourismDao.getFavoriteTourism()

    suspend fun insertTourism(tourismList: List<TourismEntity>) = tourismDao.insertTourism(tourismList)

    fun setFavoriteTourism(tourism: TourismEntity, newState: Boolean) {
        tourism.isFavorite = newState
        tourismDao.updateFavoriteTourism(tourism)
    }
}