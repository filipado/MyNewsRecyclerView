package com.example.mynewsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsrecyclerview.databinding.ArticleRowBinding
import com.example.mynewsrecyclerview.api.response.TopStory
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.mynewsrecyclerview.ui.activities.WebViewActivity


class TopStoriesAdapter : RecyclerView.Adapter<TopStoriesAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ArticleRowBinding) :
        RecyclerView.ViewHolder(binding.root)

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
        return ArticleViewHolder(
            ArticleRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]

        val photoUrl =
            if (article.multimedia[1].url.isNotEmpty())
                article.multimedia[1].url
            else ""

        val sectionString = article.section

        holder.binding.apply {
            Glide.with(imageViewCardView.context).load(photoUrl).into(ivThumbnail)
            tvTitle.text = article.title
            tvRegion.text = (sectionString[0].toUpperCase() + sectionString.substring(1))
            tvDate.text = article.published_date.split("T").first()
        }

        holder.itemView.setOnClickListener {

            Intent(it.context, WebViewActivity::class.java).apply {
                this.putExtra("URL", article.url)
                startActivity(it.context, this, null)
            }
        }

    }

}

