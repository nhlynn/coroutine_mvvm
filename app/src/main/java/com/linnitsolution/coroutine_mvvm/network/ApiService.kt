package com.linnitsolution.coroutine_mvvm.network

import com.linnitsolution.coroutine_mvvm.network.EndPoints.Companion.POSTS
import com.linnitsolution.coroutine_mvvm.network.response.PostResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(POSTS)
    suspend fun getPosts(): Response<PostResponse>
}