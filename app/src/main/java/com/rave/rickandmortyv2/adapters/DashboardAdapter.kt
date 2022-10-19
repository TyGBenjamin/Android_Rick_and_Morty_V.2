package com.rave.rickandmortyv2.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rave.rickandmortyv2.databinding.CharListBinding


class DashboardAdapter(
//    val navigate: (id: Int) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.CharListViewHolder>() {
    private var charList: List<com.example.lib_data.domain.models.Character> = emptyList()

    inner class CharListViewHolder(
        private val binding: CharListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyChar(char: com.example.lib_data.domain.models.Character) = with(binding) {
            tvTitle.text = char.name
            tvSpecies.text = char.species
            tvLife.text = char.status
            tvLocation.text = char.location.name
            imageView.load(char.image)

            root.setOnClickListener{
                Log.d("CLICK", "applyAnime: ${char.id}")
//                navigate(char.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharListViewHolder {
        val binding = CharListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharListViewHolder, position: Int) {
        val item = charList[position]
        holder.applyChar(item)
    }

    override fun getItemCount() = charList.size

    fun addItems(charList: List<com.example.lib_data.domain.models.Character>) {
        this.charList = charList
    }
}




