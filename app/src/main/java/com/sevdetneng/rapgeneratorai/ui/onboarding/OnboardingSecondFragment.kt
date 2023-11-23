package com.sevdetneng.rapgeneratorai.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.base.BaseFragment
import com.sevdetneng.rapgeneratorai.databinding.FragmentOnboardingSecondBinding

class OnboardingSecondFragment : BaseFragment<FragmentOnboardingSecondBinding>(FragmentOnboardingSecondBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingSecondNext.setOnClickListener {
            findNavController().navigate(R.id.onboardingThirdFragment)
        }
    }
}