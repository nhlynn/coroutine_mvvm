package com.linnitsolution.coroutine_mvvm.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linnitsolution.coroutine_mvvm.network.RestClient
import com.linnitsolution.coroutine_mvvm.view.PostView
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class PostViewModel : ViewModel() {
    var postView: PostView? = null

    fun setViewPost(postView: PostView) {
        this.postView = postView
    }

    fun getPosts() {
        postView!!.showProgress()
        viewModelScope.launch {
            try {
                val response = RestClient.getApiService().getPosts()
                postView!!.dismiss()
                if (response.isSuccessful) {
                    val mResponse = response.body()!!
                    postView!!.successPosts(mResponse.postList)
                } else {
                    postView!!.showError("${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                if (e.message?.lowercase(Locale.ENGLISH) != "job was cancelled") {
                    postView!!.dismiss()
                    postView!!.showError(e.message)
                }
            }
        }
    }
}