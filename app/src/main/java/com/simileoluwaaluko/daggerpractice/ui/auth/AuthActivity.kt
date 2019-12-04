package com.simileoluwaaluko.daggerpractice.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.simileoluwaaluko.daggerpractice.R
import com.simileoluwaaluko.daggerpractice.models.User
import com.simileoluwaaluko.daggerpractice.ui.main.MainActivity
import com.simileoluwaaluko.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener{

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel : AuthViewModel

    @Inject
    lateinit var logo : Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var userNumber1 : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)

        login_button.setOnClickListener(this)
        setLogo()
        subscribeObservers()

        Log.d("AuthActivity", "onCreate$userNumber1")
    }

    private fun setLogo(){
        requestManager.load(logo).into(login_logo)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            login_button.id -> {
                attemptLogin()
            }
        }
    }

    private fun attemptLogin() {
        val isEnteredTextEmpty = user_id_input.text?.isEmpty() ?: true
        if(isEnteredTextEmpty){
            return
        }

        viewModel.authenticateWithId(user_id_input.text.toString().toInt())
    }

    fun subscribeObservers(){
        viewModel.observeAuthState().observe(this,  Observer {
            if(it != null){
                when(it.status){
                    AuthResource.AuthStatus.LOADING ->{
                        showProgressBar(true)
                    }
                    AuthResource.AuthStatus.AUTHENTICATED ->{
                        showProgressBar(false)
                        onLoginSuccess()
                        Log.d("AuthActivity", "onChanged: Login success: " + it.data?.email)
                    }
                    AuthResource.AuthStatus.ERROR ->{
                        showProgressBar(false)
                        Log.d("AuthActivity", it.message ?: "")
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED ->{
                        showProgressBar(false)
                    }
                }
            }
        })
    }

    fun showProgressBar(isVisible : Boolean){
        if(isVisible) progress_bar.visibility = View.VISIBLE
        else progress_bar.visibility = View.GONE
    }

    fun onLoginSuccess(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}