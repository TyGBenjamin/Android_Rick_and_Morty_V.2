package com.rave.rickandmortyv2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class EpisodeCharAppearance : Fragment() {

    companion object {
        fun newInstance() = EpisodeCharAppearance()
    }

    private lateinit var viewModel: EpisodeCharAppearanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_char_appearance, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EpisodeCharAppearanceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}