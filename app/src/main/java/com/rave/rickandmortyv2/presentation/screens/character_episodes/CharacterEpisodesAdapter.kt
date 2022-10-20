package com.rave.rickandmortyv2.presentation.screens.character_episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lib_data.domain.models.EpisodeDetails
import com.rave.rickandmortyv2.databinding.CharacterEpisodesBinding

class CharacterEpisodesAdapter: RecyclerView.Adapter<CharacterEpisodesAdapter.CharacterEpisodesViewHolder>(){
    private var episodeList : List<EpisodeDetails> = mutableListOf()

    inner class CharacterEpisodesViewHolder(
        private val binding : CharacterEpisodesBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun displayAllEpisodes(episode: EpisodeDetails) = with(binding){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterEpisodesViewHolder {
        val binding = CharacterEpisodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterEpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterEpisodesViewHolder, position: Int) {
        val episode = episodeList[position]
        holder.displayAllEpisodes(episode)
    }

    override fun getItemCount(): Int = episodeList.size

    fun addAllEpisodes(episodes: List<EpisodeDetails>){

    }
}