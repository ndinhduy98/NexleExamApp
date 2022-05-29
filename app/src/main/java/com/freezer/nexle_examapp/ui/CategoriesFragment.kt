package com.freezer.nexle_examapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freezer.nexle_examapp.ui.adapter.CategoryAdapter
import com.freezer.nexle_examapp.data.model.Category
import com.freezer.nexle_examapp.data.viewmodel.CategoriesViewModel
import com.freezer.nexle_examapp.databinding.FragmentCategoriesBinding
import com.freezer.nexle_examapp.ui.adapter.ItemListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
  private var _binding: FragmentCategoriesBinding? = null

  private val binding get() = _binding!!

  private val categoriesViewModel: CategoriesViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
    binding.viewModel = categoriesViewModel
    binding.lifecycleOwner = this

    setUpUi()
    setUpObserver()

    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun setUpUi() {

    binding.rvCategories.apply {
      val dividerItemDecoration = DividerItemDecoration(context, GridLayoutManager.HORIZONTAL)
      adapter = CategoryAdapter(categoriesViewModel.onItemClick)
      addItemDecoration(dividerItemDecoration)
    }
  }

  private fun setUpObserver() {
    categoriesViewModel.countSelectedCategories.observe(viewLifecycleOwner) { count ->
      if(count == 0) {
        binding.textDone.visibility = View.INVISIBLE
      } else {
        binding.textDone.visibility = View.VISIBLE
      }
    }
  }
}