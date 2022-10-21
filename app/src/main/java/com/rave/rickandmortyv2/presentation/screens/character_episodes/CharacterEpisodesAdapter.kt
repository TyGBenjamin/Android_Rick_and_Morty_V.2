package com.rave.rickandmortyv2.presentation.screens.character_episodes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.EpisodeDetails
import com.rave.rickandmortyv2.databinding.CharacterEpisodesBinding

class CharacterEpisodesAdapter: RecyclerView.Adapter<CharacterEpisodesAdapter.CharacterEpisodesViewHolder>(){
    private var episodeDetailsList : MutableList<EpisodeDetails> = mutableListOf()

    inner class CharacterEpisodesViewHolder(
        private val binding : CharacterEpisodesBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun displayAllEpisodes(episode: EpisodeDetails) = with(binding){
            tvEpisodeName.text = episode.name
            val airDate = "Air Date: ${episode.airDate}"
            tvAiredDate.text = airDate
            tvEpisodeNumber.text = episode.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterEpisodesViewHolder {
        val binding = CharacterEpisodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterEpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterEpisodesViewHolder, position: Int) {
        val episode = episodeDetailsList[position]
        holder.displayAllEpisodes(episode)
    }

    override fun getItemCount(): Int = episodeDetailsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAllEpisodes(episodes: EpisodeDetails){
        episodeDetailsList.add(episodes)
        episodeDetailsList.sortBy { it -> it.id }
        notifyDataSetChanged()
    }
}