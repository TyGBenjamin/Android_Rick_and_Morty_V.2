package com.rave.rickandmortyv2.screens.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rave.rickandmortyv2.databinding.ThumbnailDashboardBinding
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.util.Constants.GET_ID_BY_URL

class DashboardAdapter(
    private val handleThumbnailClick: (id: String) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.ThumbnailViewHolder>() {
    private var characters: MutableList<Character> = mutableListOf()

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
        holder.applyItem(characters[position])

    }

    override fun getItemCount() = characters.size

    fun setCharacters(chars: List<Character>) {
        characters = chars as MutableList
    }

}