package com.rave.rickandmortyv2.presentation.screen.episode_details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lib_data.domain.models.Character
import com.rave.rickandmortyv2.databinding.CharacterBinding


class EpisodeDetailsAdapter (
    private val navToDetails:(characterId: String) -> Unit
): RecyclerView.Adapter<EpisodeDetailsAdapter.MyViewHolder>() {
    private var charData: List<Character> = mutableListOf()


    inner class MyViewHolder(
        private val binding: CharacterBinding,
    ): RecyclerView.ViewHolder(binding.root){
        fun apply(char: Character) = with(binding){
            tvName.text = char.name
            tvEpisode.text = char.episode.size.toString()
            tvRace.text = char.species
            tvLocation.text = char.location.name
            tvStatus.text = char.status
            imageView.load(char.image)

            root.setOnClickListener { navToDetails(char.id.toString()) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)


    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = charData[position]
        holder.apply(currentItem)


    }
    override fun getItemCount(): Int {
        return charData.size
    }



    @SuppressLint("NotifyDataSetChanged")
    fun addCharacterDetails(details: MutableList<Character>){
        if (details.size == 0) {
            this.charData = mutableListOf<Character>()
        } else {
            this.charData = details as MutableList<Character>
        }
        notifyDataSetChanged()
    }


}