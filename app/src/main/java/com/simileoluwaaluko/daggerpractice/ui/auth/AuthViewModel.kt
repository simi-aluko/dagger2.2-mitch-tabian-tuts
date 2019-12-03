package com.simileoluwaaluko.daggerpractice.ui.auth

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.simileoluwaaluko.daggerpractice.SessionManager
import com.simileoluwaaluko.daggerpractice.models.User
import com.simileoluwaaluko.daggerpractice.network.auth.AuthApi
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authApi : AuthApi, private val sessionManager : SessionManager) : ViewModel() {

    fun authenticateWithId(userId : Int){
        Log.d("AuthViewModel", "attempting to login")
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int) : LiveData<AuthResource<User>>{
        return LiveDataReactiveStreams
            .fromPublisher(authApi.getUser(userId)
                . onErrorReturn {
                    val errorUser = User(-1,"", "", "")
                    errorUser
                }
                // wrap User object in AuthResource
                .map(Function<User, AuthResource<User>>() {
                    if(it.id == -1)AuthResource.error("Could not authenticate", null);
                    else AuthResource.authenticated(it)
                })
                .subscribeOn(Schedulers.io()))
    }


    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}

