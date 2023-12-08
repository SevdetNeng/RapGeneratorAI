package com.okation.aivideocreator.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.SongRecyclerRowBinding
import com.okation.aivideocreator.domain.model.local.SongEntity

class SongsAdapter(val onMediaClick : (SongEntity) -> Unit,val onMoreClick : (SongEntity) -> Unit) : ListAdapter<SongEntity,SongsAdapter.ViewHolder>(SongsDiffcallback()) {
    private var selectedItem : SongEntity? = null
    inner class ViewHolder(val binding : SongRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : SongEntity){

            if(binding.root.isSelected){
                binding.songPlayPauseBtn.setImageResource(R.drawable.btn_pauserapper)
            }else{
                binding.songPlayPauseBtn.setImageResource(R.drawable.btn_playrapper)
            }
            binding.moreButton.setOnClickListener {
                onMoreClick(item)
            }
            binding.songNameText.text = item.songTitle
            binding.homeRapperImg.setImageResource(item.rapperImgId)
            binding.songPlayPauseBtn.setOnClickListener {
                onMediaClick(item)
                val lastSelectedItem = selectedItem
                if(selectedItem==item){
                    selectedItem = null
                }else{
                    selectedItem = item
                }
                notifyItemChanged(currentList.indexOf(selectedItem))
                notifyItemChanged(currentList.indexOf(lastSelectedItem))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SongRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val vh = ViewHolder(binding)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.isSelected = selectedItem == getItem(position)
        holder.bind(getItem(position))
    }

    fun unselectItem(){
        val lastSelected  = selectedItem
        selectedItem = null
        notifyItemChanged(currentList.indexOf(lastSelected))
    }
}

class SongsDiffcallback : DiffUtil.ItemCallback<SongEntity>(){
    override fun areItemsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean {
        return oldItem == newItem
    }

}