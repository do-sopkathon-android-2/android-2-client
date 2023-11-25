package com.sopt.intime.ui.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.intime.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private val binding: ActivityOnboardingBinding by lazy {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}