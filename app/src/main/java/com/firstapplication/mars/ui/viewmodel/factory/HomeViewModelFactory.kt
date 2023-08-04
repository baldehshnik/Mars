package com.firstapplication.mars.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firstapplication.mars.di.Dispatchers
import com.firstapplication.mars.domain.repository.MarsInfoRepository
import com.firstapplication.mars.ui.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val dispatchers: Dispatchers,
    private val marsInfoRepository: MarsInfoRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(dispatchers, marsInfoRepository) as T
        throw IllegalArgumentException("view model not found")
    }
}