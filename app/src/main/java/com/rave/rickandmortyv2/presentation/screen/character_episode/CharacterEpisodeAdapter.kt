package com.rave.rickandmortyv2.presentation.screen.character_episode

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode
import com.rave.rickandmortyv2.databinding.EpisodeListBinding

class CharacterEpisodeAdapter(
    private val navigateToEpisode: (episode: Int) -> Unit
):RecyclerView.Adapter<CharacterEpisodeAdapter.MyViewHolder>() {
    private var episodeData: List<Episode> = mutableListOf()

    inner class MyViewHolder(
        private val binding: EpisodeListBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun apply(episode: Episode) = with(binding){
            tvEpisodeList.text = episode.name
            root.setOnClickListener { navigateToEpisode(episode.id) }
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = EpisodeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)


    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = episodeData[position]
        holder.apply(currentItem)


    }

    override fun getItemCount(): Int {
        return episodeData.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addEpisodeDetails(details: List<Episode>){
        if (details.size == 0) {
            this.episodeData = mutableListOf<Episode>()
        } else {
            this.episodeData = details as MutableList<Episode>
        }
        notifyDataSetChanged()
    }


}