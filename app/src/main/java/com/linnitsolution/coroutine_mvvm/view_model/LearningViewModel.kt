package com.linnitsolution.coroutine_mvvm.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.linnitsolution.coroutine_mvvm.network.RestClient
import com.linnitsolution.coroutine_mvvm.network.response.PostResponse
import com.linnitsolution.coroutine_mvvm.view.PostView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.system.measureTimeMillis

class LearningViewModel(application: Application): AndroidViewModel(application) {
    var postView:PostView?=null

    fun setViewPost(postView: PostView){
        this.postView=postView
    }

    @DelicateCoroutinesApi
    fun getPosts(){
        postView!!.showProgress()
        viewModelScope.launch {
            try {
//                while (true) {
//                    val postResponse1 = RestClient.getApiService().getPosts()
//                    Log.d("LogData",postResponse1.toString())
//                }
                val time= measureTimeMillis {

//                    val postResponse1=RestClient.getApiService().getPosts()
//                    val postResponse2=RestClient.getApiService().getPosts()

                    lateinit var postResponse1: Response<PostResponse>
                    lateinit var postResponse2 : Response<PostResponse>

                    val job1=launch { postResponse1=RestClient.getApiService().getPosts() }
                    val job2=launch { postResponse2=RestClient.getApiService().getPosts() }

                    job1.join()
                    job2.join()

                    Log.d("LogData",postResponse1.toString())
                    Log.d("LogData",postResponse2.toString())


                    val response1=RestClient.getApiService().getPosts()
                    if (response1.isSuccessful) {
                        val mResponse=response1.body()!!
                        Log.d("LogData", mResponse.postList.toString())
                    }else{
                        Log.d("LogData",response1.message())
                    }
                }
                Log.d("LogData",time.toString())
            }catch (e:Exception){
                Log.d("LogData:Exception",e.message.toString())
            }

        }
    }
}