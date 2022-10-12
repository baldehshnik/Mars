package com.firstapplication.mars.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

}