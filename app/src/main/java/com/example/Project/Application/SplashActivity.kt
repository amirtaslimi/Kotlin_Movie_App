package com.example.Project.Application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import com.example.Project.R
import com.example.Project.databinding.ActivitySplashBinding
import com.example.Project.shared_component.data.Constants
import com.example.Project.utils.extensions.getBoolean

class SplashActivity : AppCompatActivity() {

    //region Properties
    private lateinit var binding: ActivitySplashBinding
    private lateinit var registerSharedPreferences: SharedPreferences
    //endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_KEY_REGISTER_TOKEN, Context.MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
            val registerToken = registerSharedPreferences.getBoolean(Constants.SHARED_PREFERENCE_KEY_REGISTER_TOKEN)

            if (registerToken) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, ActivityRegister::class.java))
            }
            finish()
        }, 1000) // 1 second delay
    }
}
