package com.artonov.watchnext

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val title: String,
    val plot: String,
    val runtime: String,
    val rating: String,
    val genre: String,
    val releaseTime: String,
    val actor: String,
    val cover: Int,
    val poster: Int
): Parcelable
