package com.rave.rickandmortyv2.presentation.screens.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import com.lib_data.resources.Resource
import com.rave.rickandmortyv2.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment: Fragment(){
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private val dashboardAdapter by lazy { DashboardAdapter(::navigateToCharacterDetails, ::getFirstSeenIn) }
//    private val characterInfo by lazy { CharacterInfo() }

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
    }

    private fun initViews() = with(binding){
        dashboardRecyclerView.adapter = dashboardAdapter
        collectLatestLifecycleFlow(viewModel.characterList){ characters ->
            characters?.let{
                dashboardAdapter.submitData(characters)
            }
        }
        collectLatestLifecycleFlow(viewModel.firstSeenIn){ episode ->
            when (episode) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val seen = episode.data.name
//                    episode.data.characters[0]

                }
                else -> {}
            }
        }
    }

    private fun navigateToCharacterDetails(id: Int){
        findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToCharacterDetailsFragment(id))
    }

    private fun getFirstSeenIn(id: Int)  {
//        return viewModel.getEpisodeById(id)
    }
}