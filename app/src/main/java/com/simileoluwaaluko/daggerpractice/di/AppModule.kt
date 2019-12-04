package com.simileoluwaaluko.daggerpractice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.simileoluwaaluko.daggerpractice.R
import com.simileoluwaaluko.daggerpractice.models.User
import com.simileoluwaaluko.daggerpractice.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Module
    companion object{

        @Singleton
        @JvmStatic
        @Provides
        fun provideRequestOptions() : RequestOptions {
            return RequestOptions
                .placeholderOf(R.drawable.simi_portrait)
                .error(R.drawable.simi_portrait)
        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager {
            return Glide.with(application).setDefaultRequestOptions(requestOptions)
        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideAppDrawable(application: Application) : Drawable {
            return ContextCompat.getDrawable(application, R.drawable.download)!!
        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideRetrofitInstance() : Retrofit{
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Singleton
        @JvmStatic
        @Provides
        fun someUser() : User {
            return User(0,"", "", "")
        }
    }
}