package com.sopt.intime.ui.onboarding

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter : RecyclerView.Adapter<OnboardingViewHolder>() {
    private val items: List<String> = listOf("123", "456", "789")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder =
        OnboardingViewHolder.from(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
