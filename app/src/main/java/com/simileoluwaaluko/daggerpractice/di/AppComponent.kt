package com.simileoluwaaluko.daggerpractice.di

import android.app.Application
import com.simileoluwaaluko.daggerpractice.BaseApplication
import com.simileoluwaaluko.daggerpractice.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


//service

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class

    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager() : SessionManager

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application : Application) : Builder

        fun build() : AppComponent
    }
}