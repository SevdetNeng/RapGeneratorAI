package com.sevdetneng.rapgeneratorai.presentation.rapper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.databinding.RapperRecyclerRowBinding
import com.sevdetneng.rapgeneratorai.domain.model.local.Rapper
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.Beat
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.rapper.Voice
import com.sevdetneng.rapgeneratorai.util.DummyData

class RapperAdapter(val onClick : (Rapper,Int) -> Unit) : ListAdapter<Rapper,RapperAdapter.ViewHolder>(RapperDiffCallback()) {
    private var lastSelectedItemPos = -1
    private var selectedItemPos = -1
    inner class ViewHolder(val binding : RapperRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Rapper){
            binding.apply {
                rapperNameText.text = item.rapperName
                rapperImg.setImageResource(item.rapperImg!!)
                if(root.isSelected){
                    rapperPlayPauseBtn.setImageResource(R.drawable.btn_pauserapper)
                }else{
                    rapperPlayPauseBtn.setImageResource(R.drawable.btn_playrapper)
                }
                rapperPlayPauseBtn.setOnClickListener {
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
        val binding = RapperRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val vh = ViewHolder(binding)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.isSelected = selectedItemPos == position
        holder.binding.rapperImg.setImageResource(DummyData.rapperImages.get(position))
        holder.bind(getItem(position))
    }

    fun setIsPlayingFalse(){
        selectedItemPos = -1
        notifyItemChanged(lastSelectedItemPos)
        lastSelectedItemPos = -1
    }

    fun getRapper(index : Int) : Rapper {
        return getItem(index)
    }
}

class RapperDiffCallback : DiffUtil.ItemCallback<Rapper>(){
    override fun areItemsTheSame(oldItem: Rapper, newItem: Rapper): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Rapper, newItem: Rapper): Boolean {
        return oldItem == newItem
    }

}