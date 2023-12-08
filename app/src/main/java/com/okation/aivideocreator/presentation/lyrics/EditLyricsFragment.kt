package com.okation.aivideocreator.presentation.lyrics

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentEditLyricsBinding


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
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}