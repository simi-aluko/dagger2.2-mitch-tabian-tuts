package com.simileoluwaaluko.daggerpractice.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.simileoluwaaluko.daggerpractice.SessionManager
import com.simileoluwaaluko.daggerpractice.models.Post
import com.simileoluwaaluko.daggerpractice.network.main.MainApi
import com.simileoluwaaluko.daggerpractice.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor( private val sessionManager: SessionManager, private val mainApi: MainApi) : ViewModel() {
    val TAG = "PostsViewModel"

    lateinit var posts : MediatorLiveData<Resource<List<Post>>>

    fun observePosts() : LiveData<Resource<List<Post>>>{
        if(!::posts.isInitialized) {
            posts = MediatorLiveData()
            posts.value = Resource.loading(listOf())

            val source = LiveDataReactiveStreams.fromPublisher(
                mainApi.getPostsFromUser(sessionManager.getAuthUser().value!!.data!!.id)
                    .onErrorReturn {
                        val posts = arrayListOf<Post>()
                        posts.add(Post(0, -1, "", ""))
                        posts
                    }
                    .map {
                        if(it.size > 0){
                            if(it[0].id == -1){
                                Resource.error("something went wrong", null)
                            }
                        }
                        Resource.success(it)
                    }
                    .subscribeOn(Schedulers.io())
            )
            posts.addSource(source, Observer {
                posts.value = it
                posts.removeSource(source)
            })
        }
        return posts
    }
}