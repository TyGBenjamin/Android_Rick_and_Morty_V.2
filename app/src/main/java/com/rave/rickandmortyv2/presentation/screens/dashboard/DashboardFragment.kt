package com.rave.rickandmortyv2.presentation.screens.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import com.lib_data.resources.Resource
import com.rave.rickandmortyv2.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class DashboardFragment: Fragment(){
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private val dashboardAdapter by lazy { DashboardAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() = with(binding){

        collectLatestLifecycleFlow(viewModel.characterList){ characterList ->
            when (characterList) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {}
//                    rvDashboardRecyclerView.adapter = dashboardAdapter.apply {
//                        addSongs(songList.data.songs)
//                    }
            }
        }
    }

    private fun initListeners() {

    }

}