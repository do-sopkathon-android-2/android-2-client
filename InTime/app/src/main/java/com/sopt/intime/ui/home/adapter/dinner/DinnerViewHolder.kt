package com.sopt.intime.ui.home.adapter.dinner

import androidx.recyclerview.widget.RecyclerView
import com.sopt.intime.data.remote.response.DataContent
import com.sopt.intime.databinding.ItemToDoListBinding

class DinnerViewHolder(
    private val binding: ItemToDoListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(dataContent: DataContent) {
        binding.tvToDoListContent.text = dataContent.content
    }
}