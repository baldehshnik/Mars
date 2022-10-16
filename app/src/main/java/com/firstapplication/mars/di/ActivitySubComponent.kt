package com.firstapplication.mars.di

import com.firstapplication.mars.ui.fragments.HomeFragment
import dagger.Subcomponent

@Subcomponent
@ActivityScope
interface ActivitySubComponent {

    fun inject(fragment: HomeFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ActivitySubComponent
    }

}