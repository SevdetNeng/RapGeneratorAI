package com.sevdetneng.rapgeneratorai.presentation.beats

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.base.BaseFragment
import com.sevdetneng.rapgeneratorai.databinding.FragmentBeatBinding
import com.sevdetneng.rapgeneratorai.presentation.SharedViewModel
import com.sevdetneng.rapgeneratorai.presentation.beats.adapter.BeatsAdapter
import com.sevdetneng.rapgeneratorai.util.BeatsItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeatFragment : BaseFragment<FragmentBeatBinding>(FragmentBeatBinding::inflate) {
    private var mediaPlayer : MediaPlayer = MediaPlayer()
    private val beatViewModel : BeatViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private val beatAdapter = BeatsAdapter(){ item,pos ->
        if(beatViewModel.isPlaying.value){
            if(beatViewModel.playingBeatIndex.value == pos){
                stopPlaying()
            }else{
                startANewBeat(item.url!!,pos)
            }
        }else{
            startANewBeat(item.url!!,pos)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            beatViewModel.beats.collect(){
                beatAdapter.submitList(it.backing_tracks)
            }
        }
        setupBeatRecycler()
        setMediaPlayerListener()

        viewLifecycleOwner.lifecycleScope.launch {
            beatViewModel.isPlaying.collect(){
                binding.beatsContinue.isSelected = it
                binding.beatsContinue.isEnabled = it
            }
        }
        binding.beatsContinue.setOnClickListener {
            if(it.isSelected){
                sharedViewModel.beat = beatAdapter.getSelectedBeat(beatViewModel.playingBeatIndex.value)
                stopPlaying()
                beatAdapter.setIsPlayingFalse()
                findNavController().navigate(R.id.rapperFragment)
            }
        }
        binding.beatBack.setOnClickListener {
            mediaPlayer.stop()
            findNavController().popBackStack()
        }
        super.onViewCreated(view, savedInstanceState)

    }

    fun setupBeatRecycler(){
        binding.apply {
            beatRecycler.adapter = beatAdapter
            beatRecycler.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)
            val x = (resources.displayMetrics.density*16).toInt()
            binding.beatRecycler.addItemDecoration(BeatsItemDecoration(x))
        }
    }

    fun startANewBeat(url : String,pos : Int){
        mediaPlayer.apply {
            reset()
            setDataSource(url)
            prepare()
            start()
        }
        beatViewModel.setIsPlaying(true)
        beatViewModel.setPlayingIndex(pos)
    }

    fun stopPlaying(){
        mediaPlayer.stop()
        beatViewModel.setIsPlaying(false)
        beatViewModel.setPlayingIndex(-1)
    }

    fun setMediaPlayerListener(){
        mediaPlayer.setOnCompletionListener {
            stopPlaying()
            beatAdapter.setIsPlayingFalse()
        }
    }
}