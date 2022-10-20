package com.rave.rickandmortyv2.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.utils.Constants
import com.rave.rickandmortyv2.databinding.EpisodeListBinding
import com.rave.rickandmortyv2.databinding.FragmentCharacterEpisodeListBinding
import com.rave.rickandmortyv2.databinding.ResidentListBinding


class EpisodeAdapter(
    val navigate: (id: Int) -> Unit,
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeListViewHolder>() {
    private var episodeList: MutableList<Episode> = mutableListOf()

    inner class EpisodeListViewHolder(
        private val binding: EpisodeListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyEpisode(episode: Episode) = with(binding) {
            tvTitleEp.text = episode.name
            tvAirdate.text = episode.air_date
            tvEpisodeNumber.text = episode.episode

            root.setOnClickListener{
                println("root clicked")
                Log.d("CLICK", "applyChar: ${episode}")
                navigate(Constants.getIdFromUrl(episode.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeListViewHolder {
        val binding = EpisodeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeListViewHolder, position: Int) {
        println("list $episodeList")
        val item = episodeList[position]
        holder.applyEpisode(item)
    }

    override fun getItemCount() = episodeList.size

    fun addItems(list: MutableList<Episode>) {
        println("adding items $list")
        episodeList = list
    }
}
