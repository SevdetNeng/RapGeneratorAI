package com.sevdetneng.rapgeneratorai.presentation.lyrics.generating

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sevdetneng.rapgeneratorai.base.BaseFragment
import com.sevdetneng.rapgeneratorai.databinding.FragmentGeneratingLyricsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GeneratingLyricsFragment : BaseFragment<FragmentGeneratingLyricsBinding>(FragmentGeneratingLyricsBinding::inflate) {

    private val args : GeneratingLyricsFragmentArgs by navArgs()
    private val generateLyricsViewModel : GenerateLyricsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateLyricsViewModel.postPrompt(args.prompt)
        binding.generatedPromptText.text = args.prompt

        viewLifecycleOwner.lifecycleScope.launch {
            generateLyricsViewModel.isLoading.collect(){
                if(!it){
                    findNavController().navigate(GeneratingLyricsFragmentDirections.actionGeneratingLyricsToGeneratedLyricsFragment(
                        generateLyricsViewModel.gptCompletion.value.trim()
                    ))
                }
            }
        }

    }
}