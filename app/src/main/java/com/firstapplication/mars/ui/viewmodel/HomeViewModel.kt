package com.firstapplication.mars.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firstapplication.mars.di.Dispatchers
import com.firstapplication.mars.domain.model.SideEffect
import com.firstapplication.mars.domain.repository.MarsInfoRepository
import com.firstapplication.mars.ui.utils.HomeFragmentLoadingViewState
import com.firstapplication.mars.ui.utils.setValueWithMainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatchers: Dispatchers,
    private val marsInfoRepository: MarsInfoRepository
) : ViewModel() {

    private val _state = MutableLiveData<SideEffect<HomeFragmentLoadingViewState>>()
    val state: LiveData<SideEffect<HomeFragmentLoadingViewState>> get() = _state

    fun readMarsModels() {
        _state.value = SideEffect(HomeFragmentLoadingViewState.Loading)
        viewModelScope.launch(dispatchers.ioDispatcher) {
            try {
                val properties = marsInfoRepository.getMarsProperties()
                _state.setValueWithMainDispatcher(SideEffect(HomeFragmentLoadingViewState.Loaded(properties)))
            } catch (e: Exception) {
                _state.setValueWithMainDispatcher(SideEffect(HomeFragmentLoadingViewState.Error))
            }
        }
    }

    init {
        readMarsModels()
    }
}