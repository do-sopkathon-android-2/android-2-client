package com.sopt.intime.ui.home.adapter.dinner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.intime.data.remote.response.DataContent
import com.sopt.intime.databinding.ItemToDoListBinding

class DinnerAdapter(
    val onClick: (String) -> Unit
) : ListAdapter<DataContent, DinnerViewHolder>(object :
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DinnerViewHolder {
        return DinnerViewHolder(
            ItemToDoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: DinnerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}