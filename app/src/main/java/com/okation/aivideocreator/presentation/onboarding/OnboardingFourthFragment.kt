package com.okation.aivideocreator.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.R
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentOnboardingFourthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFourthFragment : BaseFragment<FragmentOnboardingFourthBinding>(FragmentOnboardingFourthBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingFourthNext.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }


}