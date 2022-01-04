package com.example.kotlinpro.ui.fragment.location

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlinpro.R
import com.example.kotlinpro.common.base.BaseFragment
import com.example.kotlinpro.databinding.FragmentLocationBinding
import com.example.kotlinpro.ui.adapter.LocationAdapter
import com.example.kotlinpro.ui.adapter.load.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment :
    BaseFragment<LocationViewModel, FragmentLocationBinding>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter(this::setOnItemClick)

    override fun initialize() = with(binding) {
        locationAdapter.addLoadStateListener { loadStates ->
            rvLocation.isVisible = loadStates.refresh is LoadState.NotLoading
            rvLocation.isVisible = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupObserver() {
        lifecycleScope.launch {
            viewModel.fetchLocations().collectLatest {
                locationAdapter.submitData(it)
            }
        }
    }

    override fun setupRecycler() = with(binding) {
        rvLocation.layoutManager = LinearLayoutManager(context)
        rvLocation.adapter = locationAdapter.withLoadStateFooter(
            LoadStateAdapter { locationAdapter.retry() })
    }

    override fun swipeFresh() {
        binding.locationSwiperefreshLayout.setOnRefreshListener {
            locationAdapter.refresh()
        }
    }

    private fun setOnItemClick(id: Int) {
        findNavController().navigate(
            LocationFragmentDirections.actionLocationFragmentToLocatioinDetailFragment(
                id = id
            )
        )
    }
}