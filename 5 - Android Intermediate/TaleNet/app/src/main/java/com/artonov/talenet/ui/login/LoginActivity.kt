package com.artonov.talenet.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.view.View
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.artonov.talenet.R
import com.artonov.talenet.databinding.ActivityLoginBinding
import com.artonov.talenet.ui.register.RegisterActivity
import com.google.android.material.animation.AnimatorSetCompat.playTogether
import kotlinx.coroutines.NonCancellable.start
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
        window.enterTransition = Fade()
        window.exitTransition = Fade()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(700)
        val subtitle = ObjectAnimator.ofFloat(binding.tvSubtitle, View.ALPHA, 1f).setDuration(700)
        val email = ObjectAnimator.ofFloat(binding.etEmail, View.ALPHA, 1f).setDuration(700)
        val password = ObjectAnimator.ofFloat(binding.etPassword, View.ALPHA, 1f).setDuration(700)
        val login = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(700)
        val registerPrompt =
            ObjectAnimator.ofFloat(binding.tvRegister, View.ALPHA, 1f).setDuration(700)

        val together = AnimatorSet().apply {
            playTogether(registerPrompt, login)
        }

        AnimatorSet().apply {
            playSequentially(title, subtitle, email, password, together)
            start()
        }
    }
}