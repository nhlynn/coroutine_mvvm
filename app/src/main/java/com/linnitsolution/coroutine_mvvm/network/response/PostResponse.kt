package com.linnitsolution.coroutine_mvvm.network.response

import com.google.gson.annotations.SerializedName
import com.linnitsolution.coroutine_mvvm.model.PostVO

class PostResponse {
    @SerializedName("data")
    var postList: ArrayList<PostVO>? = null
}