package com.sevdetneng.rapgeneratorai.presentation.lyrics.generated

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.base.BaseFragment
import com.sevdetneng.rapgeneratorai.databinding.FragmentGeneratedLyricsBinding
import com.sevdetneng.rapgeneratorai.databinding.FragmentGeneratingLyricsBinding


class GeneratedLyricsFragment : BaseFragment<FragmentGeneratedLyricsBinding>(FragmentGeneratedLyricsBinding::inflate) {
    private val args : GeneratedLyricsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lyricsContentText.text = args.lyrics
            lyricsContentText.movementMethod = ScrollingMovementMethod()
            binding.buttonEdit.setOnClickListener {
                findNavController().navigate(GeneratedLyricsFragmentDirections.actionGeneratedLyricsFragmentToEditLyricsFragment(args.lyrics))
            }
        }


    }
}