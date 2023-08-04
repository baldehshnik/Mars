package com.firstapplication.mars.di

import android.content.Context
import com.firstapplication.mars.ui.fragment.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, AppBindsModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: HomeFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }
}