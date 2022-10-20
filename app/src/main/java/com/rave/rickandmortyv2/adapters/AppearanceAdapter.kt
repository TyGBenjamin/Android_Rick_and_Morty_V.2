package com.rave.rickandmortyv2.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.utils.Constants
import com.example.lib_data.utils.Constants.getIdFromUrl
import com.rave.rickandmortyv2.databinding.EpisodeListBinding
import com.rave.rickandmortyv2.databinding.FragmentCharacterEpisodeListBinding
import com.rave.rickandmortyv2.databinding.FragmentEpisodeCharAppearanceBinding
import com.rave.rickandmortyv2.databinding.ResidentListBinding


class AppearanceAdapter(
    val navigate: (id: Int) -> Unit,
) : RecyclerView.Adapter<AppearanceAdapter.AppearanceListViewHolder>() {
    private var charList: MutableList<com.example.lib_data.domain.models.Character> = mutableListOf()

    inner class AppearanceListViewHolder(
        private val binding: ResidentListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyItem(char: com.example.lib_data.domain.models.Character) = with(binding) {
            tvTitle.text = char.name
            tvSpecies.text = char.species
            tvLife.text = char.status
            imageView.load(char.image)

            root.setOnClickListener{
                println("root clicked")
                Log.d("CLICK", "applyChar: ${char}")
//                navigate(getIdFromUrl())
                navigate(char.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppearanceListViewHolder {
        val binding = ResidentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppearanceListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppearanceListViewHolder, position: Int) {
        println("list $charList")
        val item = charList[position]
        holder.applyItem(item)
    }

    override fun getItemCount() = charList.size

    fun addItems(list: MutableList<com.example.lib_data.domain.models.Character>) {
        println("adding items $list")
        charList = list
    }
}
