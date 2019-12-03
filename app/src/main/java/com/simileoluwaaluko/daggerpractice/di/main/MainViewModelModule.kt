package com.simileoluwaaluko.daggerpractice.di.main

import androidx.lifecycle.ViewModel
import com.simileoluwaaluko.daggerpractice.di.ViewModelKey
import com.simileoluwaaluko.daggerpractice.ui.main.posts.PostsViewModel
import com.simileoluwaaluko.daggerpractice.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel : ProfileViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel : PostsViewModel) : ViewModel
}