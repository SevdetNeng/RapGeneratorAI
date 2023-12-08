package com.okation.aivideocreator.presentation.rapper

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.okation.aivideocreator.R
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentRapperBinding
import com.okation.aivideocreator.presentation.SharedViewModel
import com.okation.aivideocreator.presentation.rapper.adapter.RapperAdapter
import com.okation.aivideocreator.util.DummyData
import com.okation.aivideocreator.util.PromptsItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RapperFragment : BaseFragment<FragmentRapperBinding>(FragmentRapperBinding::inflate) {
    private var mediaPlayer : MediaPlayer? = null
    private val rapperViewModel : RapperViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by activityViewModels()

    private val rapperAdater = RapperAdapter(){ rapper,pos ->
        if(rapperViewModel.isPlaying.value){
            if(rapperViewModel.playingIndex.value == pos){
                stopPlaying()
            }else{
                stopPlaying()
                startANewBeat(rapper.rapperVoice!!,pos)
            }
        }else{
            startANewBeat(rapper.rapperVoice!!,pos)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rapperAdater.submitList(DummyData.rappers)
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            rapperViewModel.isPlaying.collect(){
                if(it){
                    binding.buttonRapContinue.isEnabled = true
                    binding.buttonRapContinue.isSelected = true
                }else{
                    binding.buttonRapContinue.isEnabled = false
                    binding.buttonRapContinue.isSelected = false
                }
            }
        }
        binding.buttonRapContinue.setOnClickListener {
            sharedViewModel.rapper = rapperAdater.getRapper(rapperViewModel.playingIndex.value)
            mediaPlayer?.stop()
            findNavController().navigate(R.id.generatingRapFragment)
        }
        binding.rapperBack.setOnClickListener {
            mediaPlayer?.stop()
            findNavController().popBackStack()
        }

        super.onViewCreated(view, savedInstanceState)

    }
    fun setupRecycler(){
        val rapperRecycler = binding.rapperRecycler
        rapperRecycler.adapter = rapperAdater
        rapperRecycler.layoutManager = GridLayoutManager(requireContext(),2, VERTICAL,false)
        rapperRecycler.itemAnimator?.changeDuration = 0
        rapperRecycler.addItemDecoration(PromptsItemDecoration(2,16))
    }

    fun startANewBeat(url : Int,pos : Int){
        mediaPlayer = MediaPlayer.create(requireContext(),url)
        mediaPlayer?.start()
        rapperViewModel.setIsPlaying(true)
        rapperViewModel.setPlayingIndex(pos)
        mediaPlayer?.setOnCompletionListener {
            stopPlaying()
            rapperAdater.setIsPlayingFalse()
        }
    }

    fun stopPlaying(){
        mediaPlayer?.stop()
        rapperViewModel.setIsPlaying(false)
        rapperViewModel.setPlayingIndex(-1)
    }


}