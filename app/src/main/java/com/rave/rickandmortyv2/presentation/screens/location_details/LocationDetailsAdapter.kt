package com.rave.rickandmortyv2.presentation.screens.location_details

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.LocationDetails
import com.rave.rickandmortyv2.databinding.LocationDetailsBinding

class LocationDetailsAdapter: RecyclerView.Adapter<LocationDetailsAdapter.LocationDetailsViewHolder>() {
    private var characterDetailsList : MutableList<CharacterDetails> = mutableListOf()
    private var li : MutableList<String> = mutableListOf("Boo", "Hoo", "Lala")

    inner class LocationDetailsViewHolder(
        private val binding: LocationDetailsBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun displayLocationDetails(details: CharacterDetails) = with(binding){
            Log.d(TAG, "displayLocationDetails: Location Name: ${details.name}")
//            tvLocationName.text = details.name
            tvName.text = li[1]
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

    fun addLocationDetails(details: List<CharacterDetails>){
        characterDetailsList = if (characterDetailsList.isEmpty()) {
            mutableListOf()
        } else {
            Log.d(TAG, "addLocationDetails: $details")
            details as MutableList<CharacterDetails>
        }
        notifyDataSetChanged()
    }
}