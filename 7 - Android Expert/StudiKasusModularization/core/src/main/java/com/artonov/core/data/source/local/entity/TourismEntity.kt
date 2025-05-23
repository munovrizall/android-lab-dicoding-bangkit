package com.artonov.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tourism")
data class TourismEntity(
    @PrimaryKey
    val tourismId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "like")
    val like: Int,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
