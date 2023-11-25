package com.sevdetneng.rapgeneratorai.presentation.beats.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.databinding.BeatRecyclerRowBinding
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.Beat

class BeatsAdapter(val onClick : () -> Unit) : ListAdapter<Beat,BeatsAdapter.ViewHolder>(BeatsDiffCallback()) {
    inner class ViewHolder(val binding : BeatRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Beat){
            binding.beatTitleText.text = item.name
            binding.playPauseButton.setOnClickListener {
                binding.root.isSelected = !binding.root.isSelected
                if(binding.root.isSelected){
                    binding.playPauseButton.setImageResource(R.drawable.btn_pause)
                    binding.beatImg.visibility = View.VISIBLE
                }else {
                    binding.playPauseButton.setImageResource(R.drawable.btn_play_beats)
                    binding.beatImg.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BeatRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val vh = ViewHolder(binding)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BeatsDiffCallback : DiffUtil.ItemCallback<Beat>(){
    override fun areItemsTheSame(oldItem: Beat, newItem: Beat): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Beat, newItem: Beat): Boolean {
        return oldItem == newItem
    }

}