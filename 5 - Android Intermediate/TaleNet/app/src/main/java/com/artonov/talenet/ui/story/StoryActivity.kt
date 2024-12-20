package com.artonov.talenet.ui.story

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.artonov.talenet.R
import com.artonov.talenet.data.di.Injector
import com.artonov.talenet.databinding.ActivityStoryBinding
import com.artonov.talenet.ui.login.LoginActivity
import com.artonov.talenet.ui.maps.MapsActivity
import com.artonov.talenet.ui.story_add.StoryAddActivity
import kotlinx.coroutines.launch

class StoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoryBinding
    private val viewModel: StoryViewModel by viewModels { Injector.provideStoryViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val adapter = StoryAdapter()
        binding.rvStories.adapter = adapter
        binding.rvStories.layoutManager = LinearLayoutManager(this)

        viewModel.stories.observe(this) { pagingData ->
            if (pagingData != null) {
                Log.d("StoryActivity", "PagingData received: $pagingData")
                adapter.submitData(lifecycle, pagingData)
            } else {
                Log.d("StoryActivity", "PagingData is null")
            }
        }

        viewModel.isLoading.observe(this) {
            Log.d("Loading", "isLoading: $it")
            showLoading(it)
        }

        viewModel.errorMessage.observe(this) {
            if (it != null) Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        binding.fabAddstory.setOnClickListener {
            val intent = Intent(this, StoryAddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                lifecycleScope.launch {
                    viewModel.logout()
                    val intent = Intent(this@StoryActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                true
            }

            R.id.action_maps -> {
                lifecycleScope.launch {
                    val intent = Intent(this@StoryActivity, MapsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
    }
}