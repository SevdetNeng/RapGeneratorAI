package com.okation.aivideocreator.presentation.prompt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.databinding.PromptsRecyclerItemBinding
import com.okation.aivideocreator.domain.model.local.Prompt

class PromptsAdapter(val onClick: (prompt: Prompt) -> Unit) : ListAdapter<Prompt, PromptsAdapter.ViewHolder>(
    PromptsDiffCallback()
) {
    inner class ViewHolder(val binding : PromptsRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Prompt){
            binding.promptText.text = item.prompt
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PromptsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val vh = ViewHolder(binding)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
class PromptsDiffCallback() : DiffUtil.ItemCallback<Prompt>() {
    override fun areItemsTheSame(oldItem: Prompt, newItem: Prompt): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Prompt, newItem: Prompt): Boolean {
        return oldItem == newItem
    }

}