package com.rave.rickandmortyv2.presentation.screens.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lib_data.domain.models.CharacterWrapper
import com.rave.rickandmortyv2.databinding.DashboardBinding

class DashboardAdapter: RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    private var characterList: MutableList<CharacterWrapper> = mutableListOf()

    inner class DashboardViewHolder(
        private val binding: DashboardBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun displayAllCharacters(character: CharacterWrapper){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = DashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val characters = characterList[position]
        holder.displayAllCharacters(characters)
    }

    override fun getItemCount(): Int = characterList.size

    fun addCharacters(characters: MutableList<CharacterWrapper>){
        characterList = characters
    }
}