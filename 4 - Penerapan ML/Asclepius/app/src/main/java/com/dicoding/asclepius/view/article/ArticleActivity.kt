package com.dicoding.asclepius.view.article

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityArticleBinding
import com.dicoding.asclepius.helper.BottomNavigationHelper

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navBottom.selectedItemId = R.id.article
        BottomNavigationHelper.setupBottomNavigation(this, binding.navBottom)

    }
}