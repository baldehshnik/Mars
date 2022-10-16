package com.firstapplication.mars.ui.viewmodels.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firstapplication.mars.data.interfacies.MarsInfoRepository
import com.firstapplication.mars.di.ActivityScope
import com.firstapplication.mars.ui.viewmodels.HomeViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.lang.IllegalArgumentException

class OnlyMarsApiServiceViewModelFactory @AssistedInject constructor(
    @Assisted("application") private val application: Application,
    private val marsInfoRepository: MarsInfoRepository
) : ViewModelProvider.AndroidViewModelFactory(application = application) {

    @AssistedFactory
    @ActivityScope
    interface Factory {
        fun create(@Assisted("application") application: Application): OnlyMarsApiServiceViewModelFactory
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(application, marsInfoRepository) as T
        throw IllegalArgumentException("view model not found")
    }

}