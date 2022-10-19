package com.rave.rickandmortyv2.screens.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lib_data.domain.util.Constants.GET_ID_BY_URL
import com.example.lib_data.domain.util.Resource
import com.rave.rickandmortyv2.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private val adapter by lazy {
        DashboardAdapter(
            @DashboardFragment ::handleThumbnailClick,
            @DashboardFragment ::getOriginName
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.chars.collectLatest { charList ->
                when (charList) {
                    is Resource.Error -> {}
                    Resource.Loading -> {
                        Toast.makeText(context, "Fetching Data...", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Success -> {
                        rvList.adapter = adapter.apply { setCharacters(charList.data.results) }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.origin.collectLatest { origin ->
                rvList.adapter = adapter.apply { setOrigin(origin) }
            }
        }
    }

    private fun handleThumbnailClick(id: String) {
        navigateToCharDetails(id)
    }

    private fun getOriginName(url: String): String {
        return viewModel.getOriginName(GET_ID_BY_URL(url))
    }

    private fun navigateToCharDetails(charId: String) {
        val action =
            DashboardFragmentDirections.actionDashboardFragmentToCharacterDetailsFragment(charId)
        findNavController().navigate(action)
    }
}