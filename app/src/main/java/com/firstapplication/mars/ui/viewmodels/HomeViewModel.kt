package com.firstapplication.mars.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.firstapplication.mars.data.interfacies.MarsInfoRepository
import com.firstapplication.mars.di.ActivityScope
import com.firstapplication.mars.extensions.migrateToMarsModel
import com.firstapplication.mars.ui.models.MarsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ActivityScope
class HomeViewModel(
    application: Application, private val marsInfoRepository: MarsInfoRepository
) : AndroidViewModel(application) {

    private val _marsModels = MutableLiveData<List<MarsModel>>()
    val marsModels: LiveData<List<MarsModel>> get() = _marsModels

    private fun getMarsModels() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val properties = marsInfoRepository.getMarsProperties()
                    .map { it.migrateToMarsModel() }

                withContext(Dispatchers.Main) {
                    _marsModels.value = properties
                }

            } catch (e: Exception) {

            }
        }
    }

    init {
        getMarsModels()
    }

}