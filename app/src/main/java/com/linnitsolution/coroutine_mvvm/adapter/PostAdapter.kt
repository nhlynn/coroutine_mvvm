package com.linnitsolution.coroutine_mvvm.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linnitsolution.coroutine_mvvm.databinding.PostItemBinding
import com.linnitsolution.coroutine_mvvm.delegate.PostDelegate
import com.linnitsolution.coroutine_mvvm.model.PostVO

class PostAdapter(delegate: PostDelegate) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mDelegate = delegate
    private var mPostList = ArrayList<PostVO>()

    class PostViewHolder(var viewBinder: PostItemBinding) : RecyclerView.ViewHolder(viewBinder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as PostViewHolder
        val root = viewHolder.viewBinder

        val post = mPostList[position]

        root.tvTitle.text = post.title
        root.tvBody.text = post.body

        root.cvPost.setOnClickListener {
            mDelegate.onClickPost(post)
        }
    }

    override fun getItemCount(): Int {
        return mPostList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(postList: ArrayList<PostVO>) {
        this.mPostList = postList
        notifyDataSetChanged()
    }
}