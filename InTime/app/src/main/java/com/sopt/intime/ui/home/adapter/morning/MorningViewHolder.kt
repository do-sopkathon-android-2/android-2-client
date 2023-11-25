package com.sopt.intime.ui.home.adapter.morning

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sopt.intime.data.remote.response.DataContent
import com.sopt.intime.databinding.ItemToDoListBinding

class MorningViewHolder(
    private val binding: ItemToDoListBinding,
    val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(dataContent: DataContent) {
        binding.tvToDoListContent.text = dataContent.content
        if (binding.tvToDoListContent.text.isEmpty()) {
            binding.tvToDoListContent.isVisible = false
            binding.ivToDoListDelete.isVisible = false
            binding.evToDoListContent.isVisible = true
        }
        binding.evToDoListContent.let {
            it.setOnEditorActionListener { p0, actionId, p2 ->
                if (actionId == 0) {
                    onClick(it.text.toString())
                    (binding.root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                        .hideSoftInputFromWindow(binding.evToDoListContent.windowToken, 0)
                    binding.evToDoListContent.isVisible = false
                    binding.tvToDoListContent.isVisible = true
                    binding.tvToDoListContent.text = binding.evToDoListContent.text.toString()
                    binding.ivToDoListDelete.isVisible = true
                    true
                }
                false
            }
        }
    }
}