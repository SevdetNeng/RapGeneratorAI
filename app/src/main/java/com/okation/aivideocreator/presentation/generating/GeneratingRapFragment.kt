package com.okation.aivideocreator.presentation.generating

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentGeneratingRapBinding
import com.okation.aivideocreator.domain.model.local.Song
import com.okation.aivideocreator.presentation.SharedViewModel
import com.okation.aivideocreator.util.Extensions.getVerses
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GeneratingRapFragment : BaseFragment<FragmentGeneratingRapBinding>(FragmentGeneratingRapBinding::inflate) {
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private val generatingRapViewModel : GeneratingRapViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rapperRapImg.setImageResource(sharedViewModel.rapper.rapperImg!!)
        binding.generateRapperName.text = sharedViewModel.rapper.rapperName
        val backingTrack = sharedViewModel.beat.uuid
        val voiceModel = sharedViewModel.rapper.uuid
        val lyrics = sharedViewModel.lyrics
        generatingRapViewModel.generateRap(voiceModel!!,backingTrack!!,lyrics)
        println(lyrics.getVerses())
        viewLifecycleOwner.lifecycleScope.launch {
            generatingRapViewModel.isLoading.collect(){
                if(!it){
                    if(generatingRapViewModel.generatedSong.value.mix_url!=null){
                        val song = Song(
                            songUrl = generatingRapViewModel.generatedSong.value.mix_url!!,
                            rapper = sharedViewModel.rapper,
                            songTitle = generatingRapViewModel.generatedSong.value.title.toString()
                        )
                        val sharedPreferences = requireActivity().getSharedPreferences("Status",
                            Context.MODE_PRIVATE)
                        with(sharedPreferences.edit()){
                            if(sharedPreferences.getString("Status","Free") == "Free"){
                                putInt("Count",0)
                            }
                            commit()
                        }
                        findNavController().navigate(GeneratingRapFragmentDirections.actionGeneratingRapFragmentToSongFragment(song))
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }


}