package com.simileoluwaaluko.daggerpractice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.simileoluwaaluko.daggerpractice.models.User
import com.simileoluwaaluko.daggerpractice.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    val TAG = "SessionManager"

    val cachedUser = MediatorLiveData<AuthResource<User>>()

    fun authenticateWithId(source : LiveData<AuthResource<User>>){
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun logout(){
        Log.d(TAG, "logout: logging out...")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser() : LiveData<AuthResource<User>>{
        return cachedUser
    }
}
