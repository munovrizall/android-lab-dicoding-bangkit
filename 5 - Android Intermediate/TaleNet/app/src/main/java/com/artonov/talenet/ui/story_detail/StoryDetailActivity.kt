package com.artonov.talenet.ui.story_detail

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.artonov.talenet.R
import com.artonov.talenet.data.di.Injector
import com.artonov.talenet.databinding.ActivityStoryDetailBinding
import com.artonov.talenet.ui.story.StoryViewModel
import com.bumptech.glide.Glide

class StoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoryDetailBinding

    private val viewModel: StoryDetailViewModel by viewModels {
        Injector.provideStoryDetailViewModelFactory(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val storyId = intent.getStringExtra(EXTRA_STORY_ID)

        if (storyId != null) {
            viewModel.showDetailStory(storyId)
        }

        viewModel.story.observe(this) { storyResponse ->
            binding.tvDetailName.text = storyResponse.name
            binding.tvDetailDescription.text = storyResponse.description

            Glide.with(binding.ivDetailPhoto.context)
                .load(storyResponse.photoUrl)
                .placeholder(R.drawable.img_placeholder)
                .into(binding.ivDetailPhoto)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.errorMessage.observe(this) {
            if (it != null) Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        const val EXTRA_STORY_ID = "extra_story_id"
    }
}