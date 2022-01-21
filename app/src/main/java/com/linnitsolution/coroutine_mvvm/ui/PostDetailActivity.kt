package com.linnitsolution.coroutine_mvvm.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linnitsolution.coroutine_mvvm.R
import com.linnitsolution.coroutine_mvvm.databinding.ActivityPostDetailBinding
import com.linnitsolution.coroutine_mvvm.model.PostVO
import com.linnitsolution.coroutine_mvvm.util.MyConstants

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.post_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val post = intent.getParcelableExtra<PostVO>(MyConstants.POST)

        binding.tvTitle.text = post?.title
        binding.tvBody.text = post?.body

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, PostDetailActivity::class.java)
        }
    }
}