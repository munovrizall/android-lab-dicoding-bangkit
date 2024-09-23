package com.artonov.watchnext

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.artonov.watchnext.databinding.ActivityMainBinding
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val carouselList = mutableListOf<CarouselItem>()
    private val movieList = ArrayList<Movies>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.setHasFixedSize(true)
        binding.carouselItemContainer.registerLifecycle(lifecycle)

        showCarousel()
        movieList.addAll(getListMovies())
        showRecyclerList()
    }

    private fun showCarousel() {
        carouselList.add(
            CarouselItem(
                imageDrawable = R.drawable.carousel_1
            )
        )
        carouselList.add(
            CarouselItem(
                imageDrawable = R.drawable.carousel_2
            )
        )
        carouselList.add(
            CarouselItem(
                imageDrawable = R.drawable.carousel_3
            )
        )
        carouselList.add(
            CarouselItem(
                imageDrawable = R.drawable.carousel_4
            )
        )
        binding.carouselItemContainer.setData(carouselList)
    }

    private fun getListMovies(): ArrayList<Movies> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataPlot = resources.getStringArray(R.array.data_plot)
        val dataRuntime = resources.getStringArray(R.array.data_runtime)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataGenres = resources.getStringArray(R.array.data_genre)
        val dataReleaseTime = resources.getStringArray(R.array.data_release_time)
        val dataActor = resources.getStringArray(R.array.data_actor)
        val dataCover = resources.obtainTypedArray(R.array.data_cover)
        val dataPoster = resources.obtainTypedArray(R.array.data_poster)
        val listMovies = ArrayList<Movies>()

        for (i in dataTitle.indices) {
            val movies = Movies(
                dataTitle[i], dataPlot[i], dataRuntime[i],
                dataRating[i], dataCover.getResourceId(i, -1), dataReleaseTime[i],
                dataActor[i],  dataGenres[i], dataPoster.getResourceId(i, -1)
            )
            listMovies.add(movies)
        }
        return listMovies
    }

    private fun showRecyclerList() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        val listMovieAdapter = ListMovieAdapter(movieList)
        binding.rvMovies.adapter = listMovieAdapter
    }
}