package com.rave.rickandmortyv2.presentation.screens.location_details

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.LocationDetails
import com.rave.rickandmortyv2.databinding.LocationDetailsBinding

class LocationDetailsAdapter(
    private val navigateToCharacterDetails: (id: Int) -> Unit
): RecyclerView.Adapter<LocationDetailsAdapter.LocationDetailsViewHolder>() {
    private var characterDetailsList : MutableList<CharacterDetails> = mutableListOf()

    inner class LocationDetailsViewHolder(
        private val binding: LocationDetailsBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun displayLocationDetails(details: CharacterDetails) = with(binding){
            ivImage.load(details.image)
            tvName.text = details.name
            val statusGender = "${details.status} - ${details.gender}"
            tvStatusGender.text = statusGender

            llResidentDetails.setOnClickListener{
                navigateToCharacterDetails(details.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationDetailsViewHolder {
        val binding = LocationDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationDetailsViewHolder, position: Int) {
        val details = characterDetailsList[position]
        holder.displayLocationDetails(details)
    }

    override fun getItemCount(): Int {
        return characterDetailsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addLocationDetails(details: CharacterDetails){
//
        characterDetailsList.add(details)
        notifyDataSetChanged()
    }
}