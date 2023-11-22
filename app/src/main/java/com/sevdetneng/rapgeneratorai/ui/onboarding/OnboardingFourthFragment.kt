package com.sevdetneng.rapgeneratorai.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.base.BaseFragment
import com.sevdetneng.rapgeneratorai.databinding.FragmentOnboardingFourthBinding


class OnboardingFourthFragment : BaseFragment<FragmentOnboardingFourthBinding>(FragmentOnboardingFourthBinding::inflate) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding_fourth, container, false)
    }


}