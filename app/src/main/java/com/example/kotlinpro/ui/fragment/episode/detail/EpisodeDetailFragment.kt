package com.example.kotlinpro.ui.fragment.episode.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlinpro.R
import com.example.kotlinpro.common.base.BaseFragment
import com.example.kotlinpro.databinding.FragmentEpisodeDetailBinding
import com.example.kotlinpro.state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>
    (R.layout.fragment_episode_detail) {

    override val viewModel: EpisodeDetailViewModel by viewModels()
    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
        getData()
    }

    private fun getData() = with(binding) {
        viewModel.episodeState.subscribe {
            loaderEpisodeDetail.isVisible = it is UIState.Loading
            groupEpisode.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    itemEpisodeDetailName.text = it.data.name
                    itemEpisodeDetailUrl.text = it.data.url
                    itemEpisodeDetailCreated.text = it.data.created
                }
            }
        }
    }

    override fun initialize() {}

    override fun setupObserver() {}

    override fun setupRecycler() {}

    override fun swipeFresh() {}
}