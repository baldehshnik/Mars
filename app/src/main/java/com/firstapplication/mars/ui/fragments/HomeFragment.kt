package com.firstapplication.mars.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapplication.mars.App
import com.firstapplication.mars.R
import com.firstapplication.mars.databinding.FragmentHomeBinding
import com.firstapplication.mars.di.ActivitySubComponent
import com.firstapplication.mars.ui.activities.MainActivity
import com.firstapplication.mars.ui.adapters.MarsAdapter
import com.firstapplication.mars.ui.viewmodels.HomeViewModel
import com.firstapplication.mars.ui.viewmodels.factories.OnlyMarsApiServiceViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var activityComponent: ActivitySubComponent? = null

    private lateinit var marsAdapter: MarsAdapter

    @Inject
    lateinit var onlyMarsApiServiceViewModelFactory: OnlyMarsApiServiceViewModelFactory.Factory

    private val viewModel: HomeViewModel by viewModels {
        onlyMarsApiServiceViewModelFactory.create(activity?.application as App)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        activityComponent = (activity as MainActivity).activityComponent
        activityComponent?.inject(this)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        marsAdapter = MarsAdapter()

        with (binding) {
            rwMarsProperties.adapter = marsAdapter
            rwMarsProperties.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.marsModels.observe(viewLifecycleOwner) { marsModels ->
            marsAdapter.submitList(marsModels)
        }

    }

}