package com.sopt.intime.ui.home.adapter.lunch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.intime.data.remote.response.DataContent
import com.sopt.intime.databinding.ItemToDoListBinding

class LunchAdapter(
    val onClick: (String) -> Unit
) : ListAdapter<DataContent, LunchViewHolder>(object :
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LunchViewHolder {
        return LunchViewHolder(
            ItemToDoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: LunchViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}