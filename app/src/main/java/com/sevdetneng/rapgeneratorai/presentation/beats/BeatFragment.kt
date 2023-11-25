package com.sevdetneng.rapgeneratorai.presentation.beats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.base.BaseFragment
import com.sevdetneng.rapgeneratorai.databinding.FragmentBeatBinding
import com.sevdetneng.rapgeneratorai.presentation.beats.adapter.BeatsAdapter
import com.sevdetneng.rapgeneratorai.util.BeatsItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeatFragment : BaseFragment<FragmentBeatBinding>(FragmentBeatBinding::inflate) {
    private val beatViewModel : BeatViewModel by viewModels()
    private val beatAdapter = BeatsAdapter(){

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            beatViewModel.beats.collect(){
                beatAdapter.submitList(it.backing_tracks)
            }
        }
        setupBeatRecycler()
        super.onViewCreated(view, savedInstanceState)

    }

    fun setupBeatRecycler(){
        binding.beatRecycler.adapter = beatAdapter
        binding.beatRecycler.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)
        val x = (resources.displayMetrics.density*16).toInt()
        binding.beatRecycler.addItemDecoration(BeatsItemDecoration(x))
    }
}