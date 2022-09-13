package com.ianpedraza.login.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ianpedraza.login.R
import com.ianpedraza.login.databinding.ActivityLoginBinding
import com.ianpedraza.login.ui.home.HomeActivity
import com.ianpedraza.login.utils.viewBinding

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        subscribeObservers()
        setupUI()
    }

    private fun setupUI() {
        binding.btnLogin.setOnClickListener {
            validateLogin()
        }
    }

    private fun validateLogin() {
        with(binding) {
            val username = tietLoginUsername.text.toString()
            val password = tietLoginPassword.text.toString()
            viewModel.validateLogin(username, password)
        }
    }

    private fun subscribeObservers() {
        viewModel.showUsernameError.observe(this) { event ->
            event.getContentIfNotHandled()?.let { showUsernameError ->
                showUsernameError(showUsernameError)
            }
        }

        viewModel.showPasswordError.observe(this) { event ->
            event.getContentIfNotHandled()?.let { showPasswordError ->
                showPasswordError(showPasswordError)
            }
        }

        viewModel.loginSuccess.observe(this) { event ->
            event.getContentIfNotHandled()?.let { username ->
                goNext(username)
            }
        }
    }

    private fun showUsernameError(showUsernameError: Boolean) {
        binding.tilLoginUsername.error = if (showUsernameError) {
            getString(R.string.error_username)
        } else {
            null
        }
    }

    private fun showPasswordError(showPasswordError: Boolean) {
        binding.tilLoginPassword.error = if (showPasswordError) {
            getString(R.string.error_password)
        } else {
            null
        }
    }

    private fun goNext(username: String) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(HomeActivity.PARAM_USERNAME, username)
        }

        startActivity(intent)
    }

}