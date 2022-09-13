package com.ianpedraza.login.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ianpedraza.login.databinding.ActivityHomeBinding
import com.ianpedraza.login.utils.viewBinding

class HomeActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityHomeBinding::inflate)

    companion object {
        const val PARAM_USERNAME = "PARAM_USERNAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            val username = intent.extras?.get(PARAM_USERNAME).toString()
            tvHome.text = username
        }
    }
}