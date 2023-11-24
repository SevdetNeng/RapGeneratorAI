package com.sevdetneng.rapgeneratorai.presentation.lyrics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.base.BaseFragment
import com.sevdetneng.rapgeneratorai.databinding.FragmentEditLyricsBinding


class EditLyricsFragment : BaseFragment<FragmentEditLyricsBinding>(FragmentEditLyricsBinding::inflate) {
    private val args : EditLyricsFragmentArgs by navArgs()
    private var _changedText = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editLyricsText.setText(args.lyrics)
        binding.editLyricsText.addTextChangedListener {
            _changedText = it.toString()
        }
        binding.buttonSave.setOnClickListener {
            findNavController().navigate(EditLyricsFragmentDirections.actionEditLyricsFragmentToGeneratedLyricsFragment(_changedText))
        }

    }
}