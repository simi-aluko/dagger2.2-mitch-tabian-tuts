package com.simileoluwaaluko.daggerpractice.di.auth

import com.simileoluwaaluko.daggerpractice.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class AuthModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideAuthApi(retrofit: Retrofit) : AuthApi{
            return retrofit.create(AuthApi::class.java)
        }
    }
}