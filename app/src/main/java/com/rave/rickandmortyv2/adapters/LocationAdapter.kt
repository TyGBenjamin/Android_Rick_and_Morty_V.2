package com.rave.rickandmortyv2.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lib_data.domain.models.LocationDetails
import com.example.lib_data.utils.Constants.getIdFromUrl
import com.rave.rickandmortyv2.databinding.ResidentListBinding


class LocationAdapter(
    val navigate: (id: Int) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocateListViewHolder>() {
    private var charList: MutableList<com.example.lib_data.domain.models.Character> = mutableListOf()

    inner class LocateListViewHolder(
        private val binding: ResidentListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun apply(resident: com.example.lib_data.domain.models.Character) = with(binding) {
           tvTitle.text = resident.location.name
            tvLife.text = resident.status
            imageView.load(resident.image)
            tvSpecies.text = resident.species

            root.setOnClickListener{
                println("root clicked")
                Log.d("CLICK", "applyChar: ${resident}")
                navigate(getIdFromUrl(resident.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocateListViewHolder {
        val binding = ResidentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocateListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocateListViewHolder, position: Int) {
        println("list ${charList}List")
        val item = charList[position]
        holder.apply(item)
    }

    override fun getItemCount() = charList.size
//
//    fun addItems(list: List<String>) {
//        locateList = list as MutableList
//    }

    fun addResidents(newList: MutableList<com.example.lib_data.domain.models.Character>) {
        charList = newList
        notifyDataSetChanged()
    }
}




