package com.firstapplication.mars.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.firstapplication.mars.R
import com.firstapplication.mars.databinding.FragmentHomeBinding
import com.firstapplication.mars.domain.model.MarsModel
import com.firstapplication.mars.ui.adapter.MarsAdapter
import com.firstapplication.mars.ui.utils.HomeFragmentLoadingViewState
import com.firstapplication.mars.ui.utils.OnMarsImageClickListener
import com.firstapplication.mars.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(), OnMarsImageClickListener {

    private var recompose = false

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false).also {
            _binding = FragmentHomeBinding.bind(it)
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()

        if (savedInstanceState != null || recompose) {
            recompose = false
            viewModel.readMarsModels()
        }

        val marsAdapter = MarsAdapter(this)
        binding.rwMarsProperties.adapter = marsAdapter
        binding.rwMarsProperties.layoutManager = GridLayoutManager(
            requireContext(),
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
        )

        binding.btnReload.setOnClickListener { viewModel.readMarsModels() }

        viewModel.state.observe(viewLifecycleOwner) { result ->
            result.get()?.let { handleViewState(it, marsAdapter) }
        }
    }

    override fun onClick(model: MarsModel, imageView: ImageView) {
        val extras = FragmentNavigatorExtras(imageView to getStringValue(R.string.transition_name))
        findNavController().navigate(HomeFragmentDirections.actionNavHomeToNavDetail(model), extras)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        recompose = true
    }

    private fun setToolbar() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
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