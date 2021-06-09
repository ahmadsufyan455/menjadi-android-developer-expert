package com.fyndev.moviecatalogue.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fyndev.moviecatalogue.core.domain.model.TvShow
import com.fyndev.moviecatalogue.databinding.RvItemBinding

class FavoriteTvShowAdapter : RecyclerView.Adapter<FavoriteTvShowAdapter.ViewHolder>() {

    private val tvShows = ArrayList<TvShow>()
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun setData(list: List<TvShow>) {
        tvShows.clear()
        tvShows.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size

    inner class ViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
        fun bind(tvShow: TvShow) {
            binding.ivPoster.load(imageUrl + tvShow.poster_path) {
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(10f))
            }
            binding.tvTitle.text = tvShow.name
            binding.tvDescription.text = tvShow.overview
            itemView.setOnClickListener { onItemClickCallBack.onItemClicked(tvShow) }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(tvShow: TvShow)
    }

}