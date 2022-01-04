package com.example.kotlinpro.ui.fragment.episode

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlinpro.R
import com.example.kotlinpro.common.base.BaseFragment
import com.example.kotlinpro.databinding.FragmentEpisodeBinding
import com.example.kotlinpro.ui.adapter.EpisodeAdapter
import com.example.kotlinpro.ui.adapter.load.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@AndroidEntryPoint
class EpisodeFragment :
    BaseFragment<EpisodeViewModel, FragmentEpisodeBinding>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private val episodeAdapter = EpisodeAdapter(this::setOnItemListener)

    override fun initialize() = with(binding){
        episodeAdapter.addLoadStateListener { loadStates ->
            rvEpisode.isVisible = loadStates.refresh is LoadState.NotLoading
            episodeProgressBar.isVisible = loadStates.refresh is LoadState.Loading
            episodeSwiperefreshLayout.isRefreshing = false
        }
    }

    override fun setupObserver() {
        lifecycleScope.launch {
            viewModel.fetchEpisodes().collectLatest {
                episodeAdapter.submitData(it)
            }
        }
    }

    override fun setupRecycler() = with(binding){
        rvEpisode.layoutManager = LinearLayoutManager(context)
        rvEpisode.adapter = episodeAdapter.withLoadStateFooter(
            LoadStateAdapter { episodeAdapter.retry() })
    }

    override fun swipeFresh() {
        binding.episodeSwiperefreshLayout.setOnRefreshListener {
            episodeAdapter.refresh()
        }
    }

    private fun setOnItemListener(id: Int) {
        findNavController().navigate(
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(
                id = id
            )
        )
    }
}