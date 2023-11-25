package com.sopt.intime.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.intime.databinding.ItemAppGuideBinding

class OnboardingViewHolder(
    private val binding: ItemAppGuideBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.image = item
    }

    companion object {
        fun from(parent: ViewGroup): OnboardingViewHolder =
            OnboardingViewHolder(
                ItemAppGuideBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
    }
}