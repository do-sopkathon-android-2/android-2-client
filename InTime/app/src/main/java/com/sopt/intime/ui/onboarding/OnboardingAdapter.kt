package com.sopt.intime.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private lateinit var onboardingAdapter: OnboardingAdapter
    private val fragments = listOf<Fragment>(
        OnboardingProblemFragment(),
        OnboardingValueFragment(),
        OnboardingDetailFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}