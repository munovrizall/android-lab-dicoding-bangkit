package com.artonov.talenet.ui.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.artonov.talenet.R
import com.artonov.talenet.data.di.Injector
import com.artonov.talenet.databinding.ActivityRegisterBinding
import com.artonov.talenet.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels { Injector.provideRegisterViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
        window.enterTransition = Fade()
        window.exitTransition = Fade()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.editText?.text.toString()
            val email = binding.etEmail.editText?.text.toString()
            val password = binding.etPassword.editText?.text.toString()

            var isValid = true

            if (name.isEmpty()) {
                binding.etName.error = getString(R.string.et_name_error)
                isValid = false
            } else {
                binding.etName.error = null
            }

            if (email.isEmpty()) {
                binding.etEmail.error = getString(R.string.error_email_empty)
                isValid = false
            } else {
                binding.etEmail.error = null
            }

            if (password.isEmpty()) {
                binding.etPassword.error = getString(R.string.error_password_empty)
                isValid = false
            } else {
                binding.etPassword.error = null
            }

            if (isValid) {
                viewModel.register(name, email, password)
            }
        }

        viewModel.registerResult.observe(this) { response ->
            if (response != null) {
                Toast.makeText(this, getString(R.string.regist_successful), Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this, LoginActivity::class.java)
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
        val name = ObjectAnimator.ofFloat(binding.etName, View.ALPHA, 1f).setDuration(700)
        val email = ObjectAnimator.ofFloat(binding.etEmail, View.ALPHA, 1f).setDuration(700)
        val password = ObjectAnimator.ofFloat(binding.etPassword, View.ALPHA, 1f).setDuration(700)
        val register = ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(700)
        val loginPrompt = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(700)

        val together = AnimatorSet().apply {
            playTogether(loginPrompt, register)
        }

        AnimatorSet().apply {
            playSequentially(title, name, email, password, together)
            start()
        }
    }
}