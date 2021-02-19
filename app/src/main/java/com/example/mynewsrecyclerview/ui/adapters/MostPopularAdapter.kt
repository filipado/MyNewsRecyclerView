package com.example.mynewsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsrecyclerview.api.response.MostPopularArticle
import com.example.mynewsrecyclerview.databinding.ArticleRowBinding

class MostPopularAdapter : RecyclerView.Adapter<MostPopularAdapter.MostPopularViewHolder>() {


    private val diffCallBack = object : DiffUtil.ItemCallback<MostPopularArticle>() {
        override fun areItemsTheSame(
            oldItem: MostPopularArticle,
            newItem: MostPopularArticle
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: MostPopularArticle,
            newItem: MostPopularArticle
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var mostPopularArticles: List<MostPopularArticle>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularViewHolder {
        return MostPopularViewHolder(
            ArticleRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MostPopularViewHolder, position: Int) {
        val mostPopularArticle = mostPopularArticles[position]
        val photoUrl =
            if (mostPopularArticle.media.isNotEmpty()) {
                if (mostPopularArticle.media[0].mostPopularMetaData.isNotEmpty())
                    mostPopularArticle.media[0].mostPopularMetaData[0].url
                else ""
            } else ""

        holder.binding.apply {

            Glide.with(imageViewCardView.context).load(photoUrl).into(ivThumbnail)
            tvTitle.text = mostPopularArticle.title
            tvRegion.text = mostPopularArticle.section
            tvDate.text = mostPopularArticle.published_date
        }
    }

    override fun getItemCount() = mostPopularArticles.size


    inner class MostPopularViewHolder(val binding: ArticleRowBinding) :
        RecyclerView.ViewHolder(binding.root)
}