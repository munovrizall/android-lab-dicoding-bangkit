package com.artonov.talenet.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.artonov.talenet.R
import com.artonov.talenet.data.di.Injector
import com.artonov.talenet.databinding.ActivityLoginBinding
import com.artonov.talenet.ui.home.HomeActivity
import com.artonov.talenet.ui.register.RegisterActivity
import com.google.android.material.animation.AnimatorSetCompat.playTogether
import kotlinx.coroutines.NonCancellable.start
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels { Injector.provideLoginViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
        checkUserIsLoggedIn()

        window.enterTransition = Fade()
        window.exitTransition = Fade()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.editText?.text.toString()
            val password = binding.etPassword.editText?.text.toString()

            viewModel.login(email, password)
        }

        viewModel.loginResult.observe(this) { response ->
            if (response != null) {
                Toast.makeText(this, getString(R.string.regist_successful), Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            } else {
                Toast.makeText(this, getString(R.string.regist_fail), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkUserIsLoggedIn() {
        lifecycleScope.launch {
            val user = viewModel.getUser().first()
            if (user.token.isNotEmpty()) {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@LoginActivity,
                    getString(R.string.login_needed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
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