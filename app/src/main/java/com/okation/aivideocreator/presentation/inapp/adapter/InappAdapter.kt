package com.okation.aivideocreator.presentation.inapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.databinding.InappRecyclerRowBinding
import com.okation.aivideocreator.domain.model.local.InappPackage

class InappAdapter(val onClick : (InappPackage) -> Unit) : ListAdapter<InappPackage,InappAdapter.ViewHolder>(InappDiffCallback()) {
    private var selectedItem : InappPackage? = null
    inner class ViewHolder(val binding : InappRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : InappPackage){

            if(selectedItem == item){
                binding.constraintLayout.isSelected = true
                binding.apply {
                    packagePriceText.setTextColor(Color.WHITE)
                    packageTitleText.setTextColor(Color.WHITE)
                }
            }else {
                binding.apply {
                    constraintLayout.isSelected = false
                    packagePriceText.setTextColor(Color.BLACK)
                    packageTitleText.setTextColor(Color.BLACK)
                }
            }
            binding.packagePriceText.text = item.price
            binding.packageTitleText.text = item.title
            binding.constraintLayout.setOnClickListener {
                onClick(item)
                if(selectedItem == item){
                    selectedItem = null
                    notifyItemChanged(currentList.indexOf(item))
                }else{
                    val lastSelected = selectedItem
                    selectedItem = item
                    notifyItemChanged(currentList.indexOf(lastSelected))
                    notifyItemChanged(currentList.indexOf(item))
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = InappRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val vh = ViewHolder(binding)
        return vh
    }

    fun unselectItem(){
        val item = selectedItem
        selectedItem = null
        notifyItemChanged(currentList.indexOf(item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class InappDiffCallback : DiffUtil.ItemCallback<InappPackage>(){
    override fun areItemsTheSame(oldItem: InappPackage, newItem: InappPackage): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: InappPackage, newItem: InappPackage): Boolean {
        return oldItem == newItem
    }

}