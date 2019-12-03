package com.simileoluwaaluko.daggerpractice.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.simileoluwaaluko.daggerpractice.SessionManager
import com.simileoluwaaluko.daggerpractice.models.User
import com.simileoluwaaluko.daggerpractice.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager : SessionManager) : ViewModel() {
    val TAG = "ProfileViewModel"

    fun getAuthenticatedUser(): LiveData<AuthResource<User>>{
        return sessionManager.getAuthUser()
    }
}