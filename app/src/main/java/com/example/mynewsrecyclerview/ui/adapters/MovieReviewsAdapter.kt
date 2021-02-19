package com.example.mynewsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsrecyclerview.api.response.Result
import com.example.mynewsrecyclerview.databinding.ArticleRowBinding

class MovieReviewsAdapter : RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewsViewHolder>() {

    inner class MovieReviewsViewHolder(val binding: ArticleRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.display_title == newItem.display_title
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movieReviews: List<Result>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = movieReviews.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewsViewHolder {
        return MovieReviewsViewHolder(
            ArticleRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holderMovie: MovieReviewsViewHolder, position: Int) {
        val movieReview = movieReviews[position]

        val photoUrl = if (movieReview.multimedia.src.isNotEmpty())
            movieReview.multimedia.src
        else ""

        holderMovie.binding.apply {
            Glide.with(imageViewCardView.context).load(photoUrl).into(ivThumbnail)
            tvTitle.text = movieReview.summary_short
            tvRegion.text = movieReview.byline
            tvDate.text = movieReview.opening_date
        }
    }

}

