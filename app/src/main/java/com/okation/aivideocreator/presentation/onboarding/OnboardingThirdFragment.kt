package com.okation.aivideocreator.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.R
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentOnboardingThirdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingThirdFragment : BaseFragment<FragmentOnboardingThirdBinding>(FragmentOnboardingThirdBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingThirdNext.setOnClickListener {
            findNavController().navigate(R.id.onboardingFourthFragment)
        }
    }


}