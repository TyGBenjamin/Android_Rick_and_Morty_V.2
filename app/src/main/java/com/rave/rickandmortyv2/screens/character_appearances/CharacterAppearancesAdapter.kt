package com.rave.rickandmortyv2.screens.character_appearances

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rave.rickandmortyv2.databinding.ThumbnailCharacterBinding

class CharacterAppearancesAdapter(
    private val handleThumbnailClick: (charUrl: String) -> Unit
) : RecyclerView.Adapter<CharacterAppearancesAdapter.ThumbnailViewHolder>() {
    private var characters: MutableList<String> = mutableListOf()

    inner class ThumbnailViewHolder(
        private val binding: ThumbnailCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyItem(charUrl: String) = with(binding) {
            tvCharName.text = charUrl
            root.setOnClickListener { handleThumbnailClick(charUrl) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val binding =
            ThumbnailCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThumbnailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        holder.applyItem(characters[position])
    }

    override fun getItemCount() = characters.size

    fun addItems(urls: List<String>) {
        characters = urls as MutableList
    }
}