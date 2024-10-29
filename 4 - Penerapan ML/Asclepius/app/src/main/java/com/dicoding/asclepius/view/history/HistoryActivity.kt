package com.dicoding.asclepius.view.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.helper.BottomNavigationHelper
import com.dicoding.asclepius.view.MainActivity
import com.dicoding.asclepius.view.article.ArticleActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navBottom.selectedItemId = R.id.history
        BottomNavigationHelper.setupBottomNavigation(this, binding.navBottom)

    }
}