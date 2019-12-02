package com.simileoluwaaluko.daggerpractice.di

import androidx.lifecycle.ViewModelProvider
import com.simileoluwaaluko.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelProviderFactory : ViewModelProviderFactory) :  ViewModelProvider.Factory

}