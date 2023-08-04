package com.firstapplication.mars.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.firstapplication.mars.R
import com.firstapplication.mars.appComponent
import com.firstapplication.mars.databinding.FragmentHomeBinding
import com.firstapplication.mars.ui.adapter.MarsAdapter
import com.firstapplication.mars.ui.viewmodel.HomeViewModel
import com.firstapplication.mars.ui.viewmodel.factory.HomeViewModelFactory
import dagger.Lazy
import javax.inject.Inject

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    @Inject
    lateinit var homeViewModelFactory: Lazy<HomeViewModelFactory>

    private val viewModel: HomeViewModel by viewModels { homeViewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val marsAdapter = MarsAdapter()
        binding.rwMarsProperties.adapter = marsAdapter
        binding.rwMarsProperties.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.marsModels.observe(viewLifecycleOwner) { marsModels ->
            marsAdapter.submitList(marsModels)
        }
        viewModel.showToastMessage.observe(viewLifecycleOwner) { messId ->
            toast(messId.get() ?: R.string.error)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}