package com.firstapplication.mars.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.firstapplication.mars.R
import com.firstapplication.mars.databinding.FragmentHomeBinding
import com.firstapplication.mars.ui.adapter.MarsAdapter
import com.firstapplication.mars.ui.utils.HomeFragmentLoadingViewState
import com.firstapplication.mars.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        if (savedInstanceState != null) {
            viewModel.readMarsModels()
        }

        val marsAdapter = MarsAdapter()
        binding.rwMarsProperties.adapter = marsAdapter
        binding.rwMarsProperties.layoutManager = GridLayoutManager(
            requireContext(),
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
        )

        binding.btnReload.setOnClickListener { viewModel.readMarsModels() }

        viewModel.state.observe(viewLifecycleOwner) { result ->
            result.get()?.let { handleViewState(it, marsAdapter) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeScreenVisibility(isProgressBarVisible: Boolean) {
        binding.progressBar.isVisible = isProgressBarVisible
        binding.rwMarsProperties.isVisible = !isProgressBarVisible
        binding.errorLayout.isVisible = false
    }

    private fun showReadingError(@StringRes errorId: Int) {
        binding.progressBar.isVisible = false
        binding.rwMarsProperties.isVisible = false
        binding.errorLayout.isVisible = true
        binding.errorMessage.text = getStringValue(errorId)
    }

    private fun handleViewState(result: HomeFragmentLoadingViewState, adapter: MarsAdapter) {
        when (result) {
            HomeFragmentLoadingViewState.Loading -> {
                changeScreenVisibility(isProgressBarVisible = true)
            }

            is HomeFragmentLoadingViewState.Loaded -> {
                changeScreenVisibility(isProgressBarVisible = false)
                adapter.submitList(result.value)
            }

            HomeFragmentLoadingViewState.Error -> {
                showReadingError(R.string.error)
            }
        }
    }
}