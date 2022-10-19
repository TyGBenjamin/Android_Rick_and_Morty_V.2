package com.rave.rickandmortyv2.screens.episodes_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rave.rickandmortyv2.databinding.ThumbnailEpisodeBinding

class EpisodeListAdapter(
    private val handleThumbnailClick: (episodeUrl: String) -> Unit
) : RecyclerView.Adapter<EpisodeListAdapter.ThumbnailViewHolder>() {
    private var episodes: MutableList<String> = mutableListOf()

    inner class ThumbnailViewHolder(
        private val binding: ThumbnailEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyItem(episodeUrl: String) = with(binding) {
            tvEpiName.text = episodeUrl
            root.setOnClickListener { handleThumbnailClick(episodeUrl) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val binding =
            ThumbnailEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThumbnailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        holder.applyItem(episodes[position])
    }

    override fun getItemCount() = episodes.size

    fun addItems(urls: List<String>) {
        episodes = urls as MutableList
    }
}