package com.sopt.intime.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.intime.R
import com.sopt.intime.databinding.FragmentOnboardingDetailBinding
import com.sopt.intime.ui.timeSetting.TimeSettingActivity

class OnboardingDetailFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingDetailBinding.inflate(inflater, container, false)

        binding.btnOnboardingTimetable.setOnClickListener{
            val intent = Intent(activity, TimeSettingActivity::class.java)
            startActivity(intent)
        }
        return inflater.inflate(R.layout.fragment_onboarding_detail, container, false)
    }

}