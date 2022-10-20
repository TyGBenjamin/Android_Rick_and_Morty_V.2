package com.rave.rickandmortyv2.screens.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rave.rickandmortyv2.databinding.ThumbnailDashboardBinding
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.util.Constants.GET_ID_BY_URL

class DashboardAdapter(
    private val handleThumbnailClick: (id: String) -> Unit
) : PagingDataAdapter<Character, DashboardAdapter.ThumbnailViewHolder>(COMPARATOR) {

    inner class ThumbnailViewHolder(
        private val binding: ThumbnailDashboardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyItem(char: Character) = with(binding) {
            tvThumbnailName.text = char.name
            tvThumbnailStatus.text = char.status
            tvThumbnailSpecies.text = char.species
            ivThumbnail.load(char.image)
            tvLocation.text = char.location.name

            root.setOnClickListener { handleThumbnailClick(char.id.toString()) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val binding =
            ThumbnailDashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThumbnailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        getItem(position)?.let { char ->
            holder.applyItem(char)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem

        }
    }

}