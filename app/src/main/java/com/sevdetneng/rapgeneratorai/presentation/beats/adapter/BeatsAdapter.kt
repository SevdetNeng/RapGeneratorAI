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
import okhttp3.internal.notify

class BeatsAdapter(val onClick: (Beat, Int) -> Unit) :
    ListAdapter<Beat, BeatsAdapter.ViewHolder>(BeatsDiffCallback()) {
    private var lastSelectedItemPos: Int = -1
    private var selectedItemPos: Int = -1

    inner class ViewHolder(val binding: BeatRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Beat) {
            binding.apply {
                if (root.isSelected) {
                    playPauseButton.setImageResource(R.drawable.btn_pause)
                    beatImg.visibility = View.VISIBLE
                } else {
                    playPauseButton.setImageResource(R.drawable.btn_play_beats)
                    beatImg.visibility = View.GONE
                }
                beatTitleText.text = item.name

                playPauseButton.setOnClickListener {
                    selectedItemPos = adapterPosition
                    onClick(item, selectedItemPos)
                    if (selectedItemPos == lastSelectedItemPos) {
                        selectedItemPos = -1
                    }
                    if (lastSelectedItemPos == -1) {
                        lastSelectedItemPos = selectedItemPos
                    } else {
                        notifyItemChanged(lastSelectedItemPos)
                        lastSelectedItemPos = selectedItemPos
                    }
                    notifyItemChanged(selectedItemPos)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BeatRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val vh = ViewHolder(binding)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.isSelected = position == selectedItemPos
        holder.bind(getItem(position))
    }

    fun setIsPlayingFalse() {
        selectedItemPos = -1
        notifyItemChanged(lastSelectedItemPos)
        lastSelectedItemPos = -1
    }

    fun getSelectedBeat(pos: Int): Beat {
        return getItem(pos)
    }
}

class BeatsDiffCallback : DiffUtil.ItemCallback<Beat>() {
    override fun areItemsTheSame(oldItem: Beat, newItem: Beat): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Beat, newItem: Beat): Boolean {
        return oldItem == newItem
    }

}