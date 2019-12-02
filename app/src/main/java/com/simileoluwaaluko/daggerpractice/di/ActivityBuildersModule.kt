package com.simileoluwaaluko.daggerpractice.di

import com.simileoluwaaluko.daggerpractice.di.auth.AuthModule
import com.simileoluwaaluko.daggerpractice.di.auth.AuthViewModelsModule
import com.simileoluwaaluko.daggerpractice.ui.auth.AuthActivity
import com.simileoluwaaluko.daggerpractice.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    //names a client for injection
    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class]
    )
    abstract fun contributesAuthAuthActivity() : AuthActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity
}