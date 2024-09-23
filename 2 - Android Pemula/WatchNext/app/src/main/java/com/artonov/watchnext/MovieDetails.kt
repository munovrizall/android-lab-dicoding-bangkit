package com.artonov.watchnext

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.artonov.watchnext.databinding.ActivityMovieDetailsBinding

class MovieDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataMovie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Movies>(EXTRA_MOVIE, Movies::class.java)
        } else {
            @Suppress("DEPRECATION")
             intent.getParcelableExtra<Movies>(EXTRA_MOVIE)
        }

        Log.d("Movie Details", dataMovie.toString())

        binding.apply {
            tvTitle.text = dataMovie?.title
            tvItemGenre.text = dataMovie?.actor
            tvItemActor.text = dataMovie?.releaseTime
            tvItemRelease.text = dataMovie?.genre
            tvItemRuntime.text = dataMovie?.runtime
            tvItemRating.text = dataMovie?.rating
            tvItemPlot.text = dataMovie?.plot
            if (dataMovie != null) {
                ivImagePoster.setImageResource(dataMovie.poster)
                ivCover.setImageResource(dataMovie.cover)
            }
        }

    }
}