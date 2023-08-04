package com.firstapplication.mars.ui.fragment

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

open class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    fun toast(@StringRes stringId: Int) {
        Toast.makeText(requireContext(), getStringValue(stringId), Toast.LENGTH_SHORT).show()
    }

    private fun getStringValue(@StringRes stringId: Int): String {
        return resources.getString(stringId)
    }
}