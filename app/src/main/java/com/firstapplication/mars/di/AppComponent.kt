package com.firstapplication.mars.di

import android.content.Context
import com.firstapplication.mars.ui.fragments.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, AppBindsModule::class])
@Singleton
interface AppComponent {

    fun activityComponentBuilder(): ActivitySubComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

}