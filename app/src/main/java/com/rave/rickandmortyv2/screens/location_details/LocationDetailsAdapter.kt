package com.rave.rickandmortyv2.screens.location_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rave.rickandmortyv2.databinding.ThumbnailCharacterBinding
import com.example.lib_data.domain.models.Character

class LocationDetailsAdapter(
    private val handleThumbnailClick: (charUrl: String) -> Unit
) : RecyclerView.Adapter<LocationDetailsAdapter.ThumbnailViewHolder>() {
    private var residents: MutableList<Character> = mutableListOf()

    inner class ThumbnailViewHolder(
        private val binding: ThumbnailCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyItem(resident: Character) = with(binding) {
            tvCharName.text = resident.name
            tvCharStatus.text = resident.status
            tvCharSpecies.text = resident.species
            ivCharImg.load(resident.image)
            root.setOnClickListener { handleThumbnailClick(resident.id.toString()) }
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

    fun addResidents(res: MutableList<Character>) {
        residents = res
    }
}