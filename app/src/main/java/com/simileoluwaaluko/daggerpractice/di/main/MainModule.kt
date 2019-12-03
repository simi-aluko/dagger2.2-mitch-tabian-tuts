package com.simileoluwaaluko.daggerpractice.di.main

import com.simileoluwaaluko.daggerpractice.network.main.MainApi
import com.simileoluwaaluko.daggerpractice.ui.main.posts.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideAdapter() : PostsRecyclerAdapter {
        return PostsRecyclerAdapter()
    }

    @Provides
    fun provideMainApi(retrofit: Retrofit) : MainApi {
        return retrofit.create(MainApi::class.java)
    }
}