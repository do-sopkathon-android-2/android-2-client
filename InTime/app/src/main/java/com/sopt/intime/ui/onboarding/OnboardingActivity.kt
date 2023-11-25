package com.sopt.intime.ui.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.intime.databinding.ActivityOnboardingBinding
import com.sopt.intime.ui.timeSetting.TimeSettingActivity

class OnboardingActivity : AppCompatActivity() {
    private val binding: ActivityOnboardingBinding by lazy {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnOnboardingNext.setOnClickListener {
            navigateToTimeSettingActivity()
        }
    }

    private fun navigateToTimeSettingActivity() {
        startActivity(TimeSettingActivity.from(this))
    }
}