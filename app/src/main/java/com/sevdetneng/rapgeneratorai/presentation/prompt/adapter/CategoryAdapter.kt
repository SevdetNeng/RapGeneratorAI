package com.sevdetneng.rapgeneratorai.presentation.prompt.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sevdetneng.rapgeneratorai.databinding.CategoryRecyclerItemBinding
import com.sevdetneng.rapgeneratorai.domain.model.local.Category

class CategoryAdapter(val onClick : (String) -> Unit) : ListAdapter<Category, CategoryAdapter.ViewHolder>(
    CategoryDiffcallback()
) {
    inner class ViewHolder(val binding : CategoryRecyclerItemBinding ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Category){
            binding.categoryTitle.text = item.title
            binding.layout.isSelected = item.isSelected
            if(binding.layout.isSelected){
                binding.categoryTitle.setTextColor(Color.WHITE)
            }else{
                binding.categoryTitle.setTextColor(Color.BLACK)
            }

            binding.layout.setOnClickListener {
                onClick(item.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val vh = ViewHolder(binding)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CategoryDiffcallback() : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem== newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}