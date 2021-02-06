package com.example.mynewsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsrecyclerview.databinding.ArticleRowBinding
import com.example.mynewsrecyclerview.api.response.TopStory

class TopStoriesAdapter : RecyclerView.Adapter<TopStoriesAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ArticleRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TopStory>() {
        override fun areItemsTheSame(oldItem: TopStory, newItem: TopStory): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: TopStory, newItem: TopStory): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var articles: List<TopStory>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ArticleRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]

        holder.binding.apply {
            Glide.with(root.rootView.context).load(article.multimedia[2].url).into(ivThumbnail)
            tvTitle.text = article.title
            tvRegion.text = article.section
            tvDate.text = article.published_date
        }
    }

}

