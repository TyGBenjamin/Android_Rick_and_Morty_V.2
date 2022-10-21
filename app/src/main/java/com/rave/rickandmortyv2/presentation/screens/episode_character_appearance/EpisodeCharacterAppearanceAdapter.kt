package com.rave.rickandmortyv2.presentation.screens.episode_character_appearance

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lib_data.domain.models.CharacterDetails
import com.rave.rickandmortyv2.databinding.EpisodeCharacterAppearanceBinding

class EpisodeCharacterAppearanceAdapter(
    private val navigateToCharacterDetail:(id: Int) -> Unit
): RecyclerView.Adapter<EpisodeCharacterAppearanceAdapter.EpisodeCharacterAppearanceViewModel>() {
    private var characterList : MutableList<CharacterDetails> = mutableListOf()

    inner class EpisodeCharacterAppearanceViewModel(
        private val binding: EpisodeCharacterAppearanceBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun displayCharacterDetails(character: CharacterDetails) = with(binding){
            ivImage.load(character.image)
            tvName.text = character.name
            val statusGender = "${character.status} - ${character.gender}"
            tvStatusGender.text = statusGender

            llEpisodeCharacterAppearance.setOnClickListener{
                navigateToCharacterDetail(character.id)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeCharacterAppearanceViewModel {
        val binding = EpisodeCharacterAppearanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeCharacterAppearanceViewModel(binding)
    }

    override fun onBindViewHolder(holder: EpisodeCharacterAppearanceViewModel, position: Int) {
        val characters = characterList[position]
        holder.displayCharacterDetails(characters)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCharacterDetails(details: CharacterDetails){
        characterList.add(details)
        notifyDataSetChanged()
    }
}