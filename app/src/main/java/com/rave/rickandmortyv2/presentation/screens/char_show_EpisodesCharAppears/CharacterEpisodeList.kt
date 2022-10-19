package com.rave.rickandmortyv2.presentation.screens.char_show_EpisodesCharAppears

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rave.rickandmortyv2.R

class CharacterEpisodeList : Fragment() {

    companion object {
        fun newInstance() = CharacterEpisodeList()
    }

    private lateinit var viewModel: CharacterEpisodeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_episode_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterEpisodeListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}