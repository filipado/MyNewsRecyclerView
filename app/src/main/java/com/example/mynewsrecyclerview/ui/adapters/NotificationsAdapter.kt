package com.example.mynewsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsrecyclerview.R
import com.example.mynewsrecyclerview.api.response.SearchedArticle
import com.example.mynewsrecyclerview.databinding.ArticleRowBinding

class NotificationsAdapter : RecyclerView.Adapter<NotificationsAdapter.MyViewHolder>() {

    // MY VIEW HOLDER CLASS
    inner class MyViewHolder(val binding: ArticleRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<SearchedArticle>() {
        override fun areItemsTheSame(oldItem: SearchedArticle, newItem: SearchedArticle): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: SearchedArticle,
            newItem: SearchedArticle
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var searchResults: List<SearchedArticle>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    // 1st ADAPTER METHOD TO OVERRIDE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ArticleRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // 2nd ADAPTER METHOD TO OVERRIDE
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val searchResult = searchResults[position]

        val photoUrl =
            if (searchResult.multimedia.size != 0)
                "https://static01.nyt.com/${searchResult.multimedia[0].url}"
            else ""

        holder.binding.apply {
            Glide.with(imageViewCardView.context).load(photoUrl).into(ivThumbnail)
            tvTitle.text = searchResult.abstract
            tvRegion.text = searchResult.subsection
            tvDate.text = searchResult.published_date
        }
    }

    // 3rd ADAPTER METHOD TO OVERRIDE
    override fun getItemCount() = searchResults.size

}
