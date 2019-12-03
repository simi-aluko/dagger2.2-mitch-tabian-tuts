package com.simileoluwaaluko.daggerpractice.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.simileoluwaaluko.daggerpractice.R
import com.simileoluwaaluko.daggerpractice.models.User
import com.simileoluwaaluko.daggerpractice.ui.auth.AuthResource
import com.simileoluwaaluko.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    val TAG = "ProfileFragment"

    lateinit var viewModel : ProfileViewModel

    @Inject
    lateinit var providerFactory : ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ProfileFragment was created...")

        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel::class.java)

        subscribeObservers()

    }

    private fun subscribeObservers(){
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            if(it != null){
                when(it.status){
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetails(it.data)
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        setErrorDetails(it.message)
                    }
                }
            }
        })
    }

    private fun setUserDetails(data : User?){
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }
    private fun setErrorDetails(message : String?){
        email.text = message
        username.text = "error"
        website.text = "error"
    }


}