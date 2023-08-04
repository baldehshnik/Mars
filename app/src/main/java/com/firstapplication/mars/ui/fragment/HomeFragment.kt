package com.firstapplication.mars.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.firstapplication.mars.R
import com.firstapplication.mars.databinding.FragmentHomeBinding
import com.firstapplication.mars.ui.adapter.MarsAdapter
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