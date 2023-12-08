package com.okation.aivideocreator.presentation.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.R
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentSettingsBinding
import com.okation.aivideocreator.util.Constants


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPreferences = requireActivity().getSharedPreferences("Status",Context.MODE_PRIVATE)
        val status = sharedPreferences.getString("Status","Free")
        if(status == "Premium"){
            binding.materialCardView2.visibility = View.GONE
        }
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            settingsBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
            inappButton.setOnClickListener {
                findNavController().navigate(R.id.inAppFragment)
            }
            btnForward.setOnClickListener {
                goToNeonWebsite()
            }
            contactButton.setOnClickListener {
                goToNeonWebsite()
            }
            policyButton.setOnClickListener {
                goToNeonWebsite()
            }
            helpButton.setOnClickListener {
                goToNeonWebsite()
            }
            restoreButton.setOnClickListener {
                findNavController().navigate(R.id.inAppFragment)
            }
        }

    }

    fun goToNeonWebsite(){
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(Constants.NEON_APPS_WEBSITE)
        )
        startActivity(urlIntent)
    }
}
