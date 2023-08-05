package com.firstapplication.mars.ui.utils

import com.firstapplication.mars.domain.model.MarsModel

sealed class HomeFragmentLoadingViewState {
    object Loading : HomeFragmentLoadingViewState()
    object Error : HomeFragmentLoadingViewState()
    class Loaded(val value: List<MarsModel>) : HomeFragmentLoadingViewState()
}
