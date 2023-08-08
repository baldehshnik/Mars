package com.firstapplication.mars.ui.fragment

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun getStringValue(@StringRes stringId: Int): String {
        return resources.getString(stringId)
    }
}