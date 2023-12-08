package com.okation.aivideocreator.presentation.inapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.okation.aivideocreator.R
import com.revenuecat.purchases.Package
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.getOfferingsWith
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentInAppBinding
import com.okation.aivideocreator.domain.model.local.InappPackage
import com.okation.aivideocreator.presentation.inapp.adapter.InappAdapter
import com.okation.aivideocreator.util.BeatsItemDecoration
import com.revenuecat.purchases.PurchaseParams
import com.revenuecat.purchases.purchaseWith


class InAppFragment : BaseFragment<FragmentInAppBinding>(FragmentInAppBinding::inflate) {
    private var selectedItem: InappPackage? = null
    private var revPackage: Package? = null
    private val pacList: MutableList<Package> = mutableListOf()
    private var adapter: InappAdapter = InappAdapter() {
        if (selectedItem == it) {
            selectedItem = null
            setButtonEnabled(false)

        } else {
            selectedItem = it
            setButtonEnabled(true)
            Purchases.sharedInstance.getOfferingsWith({
                // An error occurred
            }) { offerings ->
                when (selectedItem!!.title) {
                    "Weekly" -> {
                        offerings.current?.getPackage("Small")?.also {
                            revPackage = it
                        }
                    }

                    "Monthly" -> {
                        offerings.current?.getPackage("Medium")?.also {
                            revPackage = it
                        }
                    }

                    "Annual" -> {
                        offerings.current?.getPackage("Large")?.also {
                            revPackage = it
                        }
                    }
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = binding.packageRecycler
        recycler.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        recycler.addItemDecoration(BeatsItemDecoration(32))
        recycler.adapter = adapter

        //adapter.submitList(packages)
        binding.closeButton.setOnClickListener {
            findNavController().popBackStack()
        }

        val packages = mutableListOf<InappPackage>(

        )

        Purchases.sharedInstance.getOfferingsWith({
            // An error occurred
        }) { offerings ->
            offerings.current?.availablePackages?.forEach {
                pacList.add(it)
            }
            println(pacList)
            offerings.current?.getPackage("Small")?.also {
                println(it)
                val createdPackage = InappPackage(
                    "Weekly",
                    it.product.price.formatted
                )
                packages.add(createdPackage)
                //binding.packagePrice1.text = it.product.price.formatted
            }
            offerings.current?.getPackage("Medium")?.also {
                println(it)
                val createdPackage = InappPackage(
                    "Monthly",
                    it.product.price.formatted
                )
                packages.add(createdPackage)
            }
            offerings.current?.getPackage("Large")?.also {
                println(it)
                val createdPackage = InappPackage(
                    "Annual",
                    it.product.price.formatted
                )
                packages.add(createdPackage)
            }
            adapter.submitList(packages)
        }

        binding.inappContinueBtn.setOnClickListener {
            setButtonEnabled(false)
            Purchases.sharedInstance.purchaseWith(
                PurchaseParams.Builder(requireActivity(), revPackage!!).build(),
                onError = { error, _ ->
                    Log.e("errorCode: ${error.code}", error.message)
                    selectedItem = null
                    adapter.unselectItem()
                    setButtonEnabled(false)
                },
                onSuccess = { _, _ ->
                    val sharedPreferences = requireActivity().getSharedPreferences("Status",
                        Context.MODE_PRIVATE)
                    with(sharedPreferences.edit()){
                        putString("Status","Premium")
                        commit()
                    }
                    selectedItem = null
                    adapter.unselectItem()
                    setButtonEnabled(false)
                    findNavController().navigate(R.id.homeFragment)
                }
            )
        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun setButtonEnabled(isEnabled: Boolean) {
        binding.inappContinueBtn.isEnabled = isEnabled
        binding.inappContinueBtn.isSelected = isEnabled
    }

}