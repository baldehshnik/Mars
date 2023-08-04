package com.firstapplication.mars.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firstapplication.mars.R
import com.firstapplication.mars.di.Dispatchers
import com.firstapplication.mars.domain.model.MarsModel
import com.firstapplication.mars.domain.model.SideEffect
import com.firstapplication.mars.domain.repository.MarsInfoRepository
import com.firstapplication.mars.ui.setValueWithDispatcher
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dispatchers: Dispatchers,
    private val marsInfoRepository: MarsInfoRepository
) : ViewModel() {

    private val _showToastMessage = MutableLiveData<SideEffect<Int>>()
    val showToastMessage: LiveData<SideEffect<Int>> get() = _showToastMessage

    private val _marsModels = MutableLiveData<List<MarsModel>>()
    val marsModels: LiveData<List<MarsModel>> get() = _marsModels

    private fun getMarsModels() {
        viewModelScope.launch(dispatchers.ioDispatcher) {
            try {
                val properties = marsInfoRepository.getMarsProperties()
                _marsModels.setValueWithDispatcher(properties)
            } catch (e: Exception) {
                _showToastMessage.setValueWithDispatcher(SideEffect(R.string.error))
            }
        }
    }

    init {
        getMarsModels()
    }
}