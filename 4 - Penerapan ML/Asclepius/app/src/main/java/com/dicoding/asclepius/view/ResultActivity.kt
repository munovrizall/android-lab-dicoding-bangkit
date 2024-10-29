package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getStringExtra(EXTRA_RESULT)
        val prediction = intent.getStringExtra(EXTRA_PREDICT)
        val score = intent.getStringExtra(EXTRA_SCORE)
        val data = intent.getStringExtra(EXTRA_IMAGE_URI)
        val imageUri = Uri.parse(data)

        runOnUiThread {
            imageUri?.let {
                Log.d("Image URI", "showImage: $it")
                binding.resultImage.setImageURI(it)
                binding.resultText.text = result
            }
        }
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_PREDICT = "extra_predict"
        const val EXTRA_SCORE = "extra_score"
    }
}