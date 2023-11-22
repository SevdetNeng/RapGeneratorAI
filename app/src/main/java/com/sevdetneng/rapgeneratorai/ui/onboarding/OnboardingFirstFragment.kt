package com.sevdetneng.rapgeneratorai.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.base.BaseFragment
import com.sevdetneng.rapgeneratorai.databinding.FragmentOnboardingFirstBinding


class OnboardingFirstFragment() : BaseFragment<FragmentOnboardingFirstBinding>(FragmentOnboardingFirstBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding_first, container, false)
    }
}