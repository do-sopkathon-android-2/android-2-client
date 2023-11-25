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

        onboardingView()
        dotsIndicator()
    }

    private fun onboardingView(){
        val viewPager = binding.vpOnboarding
        val onboardingAdapter = OnboardingAdapter(this)
        viewPager.adapter = onboardingAdapter
    }

    private fun dotsIndicator(){
        val pagerAdapter = OnboardingAdapter(this)
        binding.vpOnboarding.adapter = pagerAdapter
        binding.vpOnboarding.setCurrentItem(1, true)
        binding.wormDotsIndicator.attachTo(binding.vpOnboarding)
    }
}