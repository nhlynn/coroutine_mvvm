package com.linnitsolution.coroutine_mvvm.view

import com.linnitsolution.coroutine_mvvm.model.PostVO

interface PostView : BaseView {
    fun successPosts(postList: ArrayList<PostVO>?)
}