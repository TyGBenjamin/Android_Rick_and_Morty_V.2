package com.rave.rickandmortyv2.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lib_data.utils.Constants.getIdFromUrl
import com.rave.rickandmortyv2.databinding.ResidentListBinding


class LocationAdapter(
    val navigate: (id: Int) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocateListViewHolder>() {
    private var locateList: MutableList<String> = mutableListOf()

    inner class LocateListViewHolder(
        private val binding: ResidentListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyResidents(resident: String) = with(binding) {
           tvTitle.text = resident

            root.setOnClickListener{
                println("root clicked")
                Log.d("CLICK", "applyChar: ${resident}")
                navigate(getIdFromUrl(resident))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocateListViewHolder {
        val binding = ResidentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocateListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocateListViewHolder, position: Int) {
        println("list $locateList")
        val item = locateList[position]
        holder.applyResidents(item)
    }

    override fun getItemCount() = locateList.size

    fun addItems(list: List<String>) {
        locateList = list as MutableList
    }
}




