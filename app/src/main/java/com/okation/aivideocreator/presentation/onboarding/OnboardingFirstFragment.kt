package com.okation.aivideocreator.presentation.onboarding

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.R
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentOnboardingFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFirstFragment() : BaseFragment<FragmentOnboardingFirstBinding>(FragmentOnboardingFirstBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = requireActivity().getSharedPreferences("Status",Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            putString("Status","Free")
            putInt("Count",1)
            commit()
        }
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingFirstNext.setOnClickListener {
            findNavController().navigate(R.id.onboardingSecondFragment)
        }
    }
}