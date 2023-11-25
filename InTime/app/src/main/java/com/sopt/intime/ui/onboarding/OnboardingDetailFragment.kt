package com.sopt.intime.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.intime.databinding.FragmentOnboardingDetailBinding
import com.sopt.intime.ui.timeSetting.TimeSettingActivity

class OnboardingDetailFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingDetailBinding.inflate(inflater, container, false)

        binding.btnOnboardingTimetable.setOnClickListener {
            startActivity(
                TimeSettingActivity.from(requireContext())
            )
        }
        return binding.root
    }
}
