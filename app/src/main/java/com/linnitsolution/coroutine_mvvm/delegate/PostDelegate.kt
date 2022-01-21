package com.linnitsolution.coroutine_mvvm.delegate

import com.linnitsolution.coroutine_mvvm.model.PostVO

interface PostDelegate {
    fun onClickPost(post: PostVO)
}