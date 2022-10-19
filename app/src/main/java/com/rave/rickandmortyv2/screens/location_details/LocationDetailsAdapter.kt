package com.rave.rickandmortyv2.screens.location_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rave.rickandmortyv2.databinding.ThumbnailCharacterBinding
import com.rave.rickandmortyv2.databinding.ThumbnailEpisodeBinding
import com.rave.rickandmortyv2.screens.episodes_list.EpisodeListAdapter

class LocationDetailsAdapter(
    private val handleThumbnailClick: (charUrl: String) -> Unit
) : RecyclerView.Adapter<LocationDetailsAdapter.ThumbnailViewHolder>() {
    private var residents: MutableList<String> = mutableListOf()

    inner class ThumbnailViewHolder(
        private val binding: ThumbnailCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyItem(residentUrl: String) = with(binding) {
            tvCharName.text = residentUrl
            root.setOnClickListener { handleThumbnailClick(residentUrl) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val binding =
            ThumbnailCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThumbnailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        holder.applyItem(residents[position])
    }

    override fun getItemCount() = residents.size

    fun addItems(urls: List<String>) {
        residents = urls as MutableList
    }
}