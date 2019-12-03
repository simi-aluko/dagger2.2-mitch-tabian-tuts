package com.simileoluwaaluko.daggerpractice.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.simileoluwaaluko.daggerpractice.R
import com.simileoluwaaluko.daggerpractice.ui.main.Resource
import com.simileoluwaaluko.daggerpractice.util.VerticalSpacingItemDecoration
import com.simileoluwaaluko.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject

class PostsFragment @Inject constructor() : DaggerFragment() {
    val TAG =  "PostsFragment"

    @Inject
    lateinit var providerFactory : ViewModelProviderFactory

    @Inject
    lateinit var adapter : PostsRecyclerAdapter

    lateinit var viewModel : PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel::class.java)
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, Observer {
            if(it != null){
                when(it.status){
                    Resource.Status.LOADING -> {
                        Log.d(TAG, "onChanged : Loading...")
                    }
                    Resource.Status.SUCCESS -> {
                        Log.d(TAG, "onChanged : got posts...")
                        adapter.setPosts(it.data)
                    }
                    Resource.Status.ERROR -> {
                        Log.d(TAG, "onChanged : Error..." + it.message)
                    }
                }
            }
        })
    }

    fun initRecyclerView(){
        recycler_view.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpacingItemDecoration(15)
        recycler_view.addItemDecoration(itemDecoration)
        recycler_view.adapter = adapter
    }
}