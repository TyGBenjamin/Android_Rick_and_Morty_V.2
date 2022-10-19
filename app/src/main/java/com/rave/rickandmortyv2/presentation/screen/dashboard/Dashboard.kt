package com.rave.rickandmortyv2.presentation.screen.dashboard

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lib_data.domain.models.Data
import com.example.lib_data.util.Resource
import com.rave.rickandmortyv2.R
import com.rave.rickandmortyv2.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Dashboard : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private val dashboardAdapter by lazy { DashboardAdapter(::navToDetails)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false).also { _binding = it }.root



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    initViews()
    }




    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.Char.collectLatest { viewState ->
                when (viewState) {
                    is Resource.Error -> viewState.message
                    is Resource.Loading -> Log.d(TAG, "initViews: Loading....")
                    is Resource.Success -> recyclerView.adapter = dashboardAdapter.apply { addData(viewState.data.results) }

                }

            }
        }

    }
    private fun navToDetails(characterId: String){
        val action = DashboardDirections.actionDashboardToCharacterDetails(characterId)
        findNavController().navigate(action)
    }

}