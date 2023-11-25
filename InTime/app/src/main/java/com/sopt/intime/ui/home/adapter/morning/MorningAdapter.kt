package com.sopt.intime.ui.home.adapter.morning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.intime.data.remote.response.DataContent
import com.sopt.intime.databinding.ItemToDoListBinding

class MorningAdapter : ListAdapter<DataContent, MorningViewHolder>(object :
    DiffUtil.ItemCallback<DataContent>() {
    override fun areItemsTheSame(oldItem: DataContent, newItem: DataContent): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DataContent,
        newItem: DataContent
    ): Boolean {
        return oldItem == newItem
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MorningViewHolder {
        return MorningViewHolder(
            ItemToDoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MorningViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}