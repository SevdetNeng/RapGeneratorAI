package com.okation.aivideocreator.presentation.lyrics.generated

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.okation.aivideocreator.R
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentGeneratedLyricsBinding
import com.okation.aivideocreator.presentation.SharedViewModel


class GeneratedLyricsFragment : BaseFragment<FragmentGeneratedLyricsBinding>(FragmentGeneratedLyricsBinding::inflate) {
    private val args : GeneratedLyricsFragmentArgs by navArgs()
    private val sharedViewModel : SharedViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lyricsContentText.text = args.lyrics
            lyricsContentText.movementMethod = ScrollingMovementMethod()
            binding.buttonEdit.setOnClickListener {
                findNavController().navigate(GeneratedLyricsFragmentDirections.actionGeneratedLyricsFragmentToEditLyricsFragment(args.lyrics))
            }
            binding.buttonContinue.setOnClickListener {
                sharedViewModel.lyrics = args.lyrics
                findNavController().navigate(R.id.beatFragment)
            }
            binding.backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }


    }
}