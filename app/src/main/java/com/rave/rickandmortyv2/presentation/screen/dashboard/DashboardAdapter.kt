package com.rave.rickandmortyv2.presentation.screen.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rave.rickandmortyv2.databinding.CharacterBinding
import com.example.lib_data.domain.models.Character

class DashboardAdapter(): RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {

    private var charData: List<Character> = emptyList()

    inner class MyViewHolder(
        private val binding: CharacterBinding,
    ): RecyclerView.ViewHolder(binding.root){
        fun apply(char: Character){
            binding.tvName.text = char.name
            binding.tvEpisode.text = char.episode.size.toString()
            binding.tvRace.text = char.species
            binding.tvStatus.text = char.status
            binding.imageView.load(char.image)
            binding.tvLocation.text = char.location.name

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

    fun addData(items: List<Character>){
        this.charData = items
    }

}