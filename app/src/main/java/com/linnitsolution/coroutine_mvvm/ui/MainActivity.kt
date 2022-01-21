package com.linnitsolution.coroutine_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.linnitsolution.coroutine_mvvm.adapter.PostAdapter
import com.linnitsolution.coroutine_mvvm.databinding.ActivityMainBinding
import com.linnitsolution.coroutine_mvvm.delegate.PostDelegate
import com.linnitsolution.coroutine_mvvm.model.PostVO
import com.linnitsolution.coroutine_mvvm.util.MyConstants
import com.linnitsolution.coroutine_mvvm.view.PostView
import com.linnitsolution.coroutine_mvvm.view_model.PostViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity(), PostView, PostDelegate {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mPostViewModel: PostViewModel
    private lateinit var mPostAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPostViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        mPostViewModel.setViewPost(this)

        mPostAdapter = PostAdapter(this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvPost.layoutManager = layoutManager
        binding.rvPost.adapter = mPostAdapter

        getPost()

        binding.srPost.setOnRefreshListener {
            binding.srPost.isRefreshing = false
            getPost()
        }
    }

    private fun getPost() {
        lifecycleScope.launch {
            mPostViewModel.getPosts()
        }
    }

    override fun successPosts(postList: ArrayList<PostVO>?) {
        if (isFinishing) {
            return
        }
        if (postList.isNullOrEmpty()) {
            binding.noDataLayout.tvNoData.visibility = View.VISIBLE
            binding.rvPost.visibility = View.GONE
        } else {
            binding.noDataLayout.tvNoData.visibility = View.GONE
            binding.rvPost.visibility = View.VISIBLE
            mPostAdapter.setData(postList)
        }
    }

    override fun showError(message: String?) {
        if (isFinishing) {
            return
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun dismiss() {
        if (isFinishing) {
            return
        }
        binding.loadingLayout.progressbar.visibility = View.GONE
    }

    override fun showProgress() {
        if (isFinishing) {
            return
        }
        binding.loadingLayout.progressbar.visibility = View.VISIBLE
    }

    override fun onClickPost(post: PostVO) {
        startActivity(PostDetailActivity.newIntent(this).putExtra(MyConstants.POST, post))
    }
}