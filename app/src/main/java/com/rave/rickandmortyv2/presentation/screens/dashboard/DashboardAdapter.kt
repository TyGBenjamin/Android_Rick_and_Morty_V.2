package com.rave.rickandmortyv2.presentation.screens.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lib_data.domain.models.CharacterDetails
import com.rave.rickandmortyv2.databinding.DashboardBinding

class DashboardAdapter(
    private val navigateToCharacterDetails: (id: Int) -> Unit,
    private val getFirstSeenIn: (id: Int) -> String,
    private val numCall: (num: Int) -> Unit
): PagingDataAdapter<CharacterDetails, DashboardAdapter.DashboardViewHolder>(COMPARATOR) {

    inner class DashboardViewHolder(
        private val binding: DashboardBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun displayAllCharacters(character: CharacterDetails) = with(binding){
            ivImage.load(character.image)
            tvName.text = character.name
            val status = "${character.status} -"
            tvStatus.text = status
            tvSpecies.text = character.species
            tvLocation.text = character.location.name
            val url = character.episode.sorted()
            val id = url[0].subSequence(40, url[0].lastIndex+1) as String
            val stupidId = id.toInt()
            val firstSeenIn = getFirstSeenIn(stupidId+1)
            tvFirstSeenIn.text = firstSeenIn
            numCall(
                    absoluteAdapterPosition
            )
            root.setOnClickListener{
                navigateToCharacterDetails(character.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = DashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val characters = getItem(position)
        characters?.let { character ->
            holder.displayAllCharacters(character)
        }

    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<CharacterDetails>() {
            override fun areItemsTheSame(
                oldItem: CharacterDetails,
                newItem: CharacterDetails
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: CharacterDetails,
                newItem: CharacterDetails
            ): Boolean = oldItem == newItem
        }
    }
}