package com.artonov.watchnext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.artonov.watchnext.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener() {
            finish()
        }
    }
}