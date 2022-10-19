package com.rave.rickandmortyv2.presentation.screens.dashboard

import android.os.Bundle
import android.util.Log
import android.view.Gravity.apply
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.lib_data.utils.Resource
import com.rave.rickandmortyv2.adapters.DashboardAdapter
import com.rave.rickandmortyv2.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Dashboard : Fragment() {

        private var _binding: FragmentDashboardBinding? = null
        private val binding: FragmentDashboardBinding get() = _binding!!
        private val viewModel by viewModels<DashboardViewModel>()
        private  val dashboardAdapter by lazy {DashboardAdapter(::navigateToDetails)}
//    private val charAdapter: CharAdapter by lazy { CharAdapter(::navigateToDetails) }

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
//        rvView.adapter = charAdapter
            lifecycleScope.launch{
                viewModel.Char.collectLatest{ viewState ->
                    when (viewState) {
                        is Resource.Error -> {
                            Log.d(TAG, "ERROR: ${viewState.message}")
                        }
                        is Resource.Loading -> {
                            Log.d(TAG, "Loading...")
                        }
                        is Resource.Success -> rvView.adapter = dashboardAdapter.apply {
                            addItems(viewState.data.results)
                            Log.d(TAG, "IM OVER HERE")
                        }

                    }
                }
            }

        }

        private fun navigateToDetails(charId: Int) {
            val action = DashboardDirections.actionDashboardToCharDetails(charId)
            findNavController().navigate(action)
        }

        companion object {
            const val TAG = "DashboardFragmentLogger"
        }
}