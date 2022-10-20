package com.rave.rickandmortyv2.screens.character_appearances

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.lib_data.domain.models.Character
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rave.rickandmortyv2.databinding.ThumbnailCharacterBinding

class CharacterAppearancesAdapter(
    private val handleThumbnailClick: (charUrl: String) -> Unit
) : RecyclerView.Adapter<CharacterAppearancesAdapter.ThumbnailViewHolder>() {
    private var characters: MutableList<Character> = mutableListOf()

    inner class ThumbnailViewHolder(
        private val binding: ThumbnailCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyItem(char: Character) = with(binding) {
            tvCharName.text = char.name
            ivCharImg.load(char.image)
            tvCharSpecies.text = char.species
            tvCharStatus.text = char.status
            root.setOnClickListener { handleThumbnailClick(char.id.toString()) }
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

    fun addCharacters(chars: MutableList<Character>) {
        characters = chars
    }
}