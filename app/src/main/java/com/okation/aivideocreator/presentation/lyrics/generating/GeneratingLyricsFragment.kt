package com.okation.aivideocreator.presentation.lyrics.generating

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.okation.aivideocreator.R
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentGeneratingLyricsBinding
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
                    if(generateLyricsViewModel.isError.value){
                        Toast.makeText(requireContext(),"timeout",Toast.LENGTH_LONG).show()
                    }else{
                        findNavController().navigate(GeneratingLyricsFragmentDirections.actionGeneratingLyricsToGeneratedLyricsFragment(
                            generateLyricsViewModel.gptCompletion.value.trim()
                        ))
                    }

                }
            }
        }

        binding.backButton.setOnClickListener {
            if(generateLyricsViewModel.isLoading.value){
                findNavController().popBackStack()
            }else{
                findNavController().navigate(R.id.promptFragment)
            }
        }

    }
}