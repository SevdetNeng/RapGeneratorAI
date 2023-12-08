package com.okation.aivideocreator.presentation.prompt

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentPromptBinding
import com.okation.aivideocreator.presentation.prompt.adapter.CategoryAdapter
import com.okation.aivideocreator.presentation.prompt.adapter.PromptsAdapter
import com.okation.aivideocreator.util.CategoriesItemDecoration
import com.okation.aivideocreator.util.DummyData
import com.okation.aivideocreator.util.PromptsItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PromptFragment : BaseFragment<FragmentPromptBinding>(FragmentPromptBinding::inflate) {

    private val promptViewModel : PromptViewModel by viewModels()

    private val categoriesAdapter = CategoryAdapter{ title->
        promptViewModel.selectCategory(title)
    }
    private val promptsAdapter = PromptsAdapter{
        binding.promptEditText.setText(it.prompt)
    }
    private var _prompt = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            promptEditText.addTextChangedListener {
                promptViewModel.setPromptEntered(it.toString().isNotEmpty())
                _prompt = it.toString()
            }
            viewLifecycleOwner.lifecycleScope.launch {
                promptViewModel.isPromptEntered.collect() {
                    continueButton.isEnabled = it
                }
            }
            continueButton.setOnClickListener {
                findNavController().navigate(PromptFragmentDirections.actionPromptFragmentToGeneratingLyrics(_prompt))
            }
        }

        setupCategoriesRecycler()
        setupPromptsRecycler()

        viewLifecycleOwner.lifecycleScope.launch {
            promptViewModel.selectedCategory.collect(){
                val newList = DummyData.categories.toMutableList()
                newList.forEachIndexed {index , category ->
                    if(category.title == it ){
                        newList[index] = newList[index].copy(category.title,true)
                    }else{
                        newList[index] = newList[index].copy(category.title,false)
                    }
                }
                val promptsList = DummyData.prompts.filter { prompt ->
                    prompt.category == it
                }
                categoriesAdapter.submitList(newList)
                promptsAdapter.submitList(promptsList)

            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            promptViewModel.isPromptEntered.collect(){
                binding.continueButton.isSelected = it
            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    fun setupCategoriesRecycler(){

        val categoriesRecycler = binding.categoriesRecycler
        categoriesRecycler.adapter = categoriesAdapter
        categoriesRecycler.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL,false)
        categoriesRecycler.addItemDecoration(
            CategoriesItemDecoration(16)
        )
    }
    fun setupPromptsRecycler(){
        val promptsRecycler = binding.promptsRecycler
        promptsRecycler.adapter = promptsAdapter
        promptsRecycler.layoutManager = GridLayoutManager(requireContext(),2)
        val x = (resources.displayMetrics.density*16).toInt()
        promptsRecycler.addItemDecoration(PromptsItemDecoration(2,x))
        promptsAdapter.submitList(DummyData.prompts)

    }
}