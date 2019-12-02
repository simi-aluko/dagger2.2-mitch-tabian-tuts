package com.simileoluwaaluko.daggerpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.simileoluwaaluko.daggerpractice.SessionManager
import com.simileoluwaaluko.daggerpractice.ui.auth.AuthActivity
import com.simileoluwaaluko.daggerpractice.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    val TAG = "BASEACTVITIY"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        sessionManager.getAuthUser().observe(this, Observer {
            if(it != null){
                when(it.status){
                    AuthResource.AuthStatus.LOADING ->{
                    }
                    AuthResource.AuthStatus.AUTHENTICATED ->{

                        Log.d("AuthActivity", "onChanged: Login success: " + it.data?.email)
                    }
                    AuthResource.AuthStatus.ERROR ->{

                        Log.d("AuthActivity", it.message ?: "")
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED ->{
                        navLoginScreen()
                    }
                }
            }
        })
    }

    private fun navLoginScreen(){
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}