package com.simileoluwaaluko.daggerpractice.di

import com.simileoluwaaluko.daggerpractice.di.auth.AuthModule
import com.simileoluwaaluko.daggerpractice.di.auth.AuthScope
import com.simileoluwaaluko.daggerpractice.di.auth.AuthViewModelsModule
import com.simileoluwaaluko.daggerpractice.di.main.MainFragmentBuildersModule
import com.simileoluwaaluko.daggerpractice.di.main.MainModule
import com.simileoluwaaluko.daggerpractice.di.main.MainScope
import com.simileoluwaaluko.daggerpractice.di.main.MainViewModelModule
import com.simileoluwaaluko.daggerpractice.ui.auth.AuthActivity
import com.simileoluwaaluko.daggerpractice.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    //names a client for injection
    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class]
    )
    abstract fun contributesAuthAuthActivity() : AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,
            MainViewModelModule::class,
            MainModule::class]
    )
    abstract fun contributeMainActivity() : MainActivity
}