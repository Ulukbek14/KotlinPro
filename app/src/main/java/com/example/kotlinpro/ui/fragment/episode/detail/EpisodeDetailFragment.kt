package com.example.kotlinpro.ui.fragment.episode.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlinpro.R
import com.example.kotlinpro.common.base.BaseFragment
import com.example.kotlinpro.databinding.FragmentEpisodeDetailBinding
import com.example.kotlinpro.state.UIState
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class EpisodeDetailFragment : BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>
    (R.layout.fragment_episode_detail) {

    override val viewModel: EpisodeDetailViewModel by viewModel()
    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
        getData()
    }

    private fun getData() = with(binding) {
        viewModel.episodeState.subscribe {
            loaderEpisodeDetail.isVisible = it is UIState.Loading
            groupEpisode.isVisible = it !is UIState.Loading
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
//@InternalCoroutinesApi
//class EpisodeDetailFragment : BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>
//    (R.layout.fragment_episode_detail) {
//
//    override val viewModel: EpisodeDetailViewModel by viewModel()
//    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
//        getData()
//    }
//
//    private fun getData() = with(binding) {
//        viewModel.episodeState.subscribe {
//            loaderEpisodeDetail.isVisible = it is UIState.Loading
//            groupEpisode.isVisible = it is UIState.Loading
//            when (it) {
//                is UIState.Error -> {
//                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
//                }
//                is UIState.Loading -> {
//
//                }
//                is UIState.Success -> {
//                    itemEpisodeDetailName.text = it.data.name
//                    itemEpisodeDetailUrl.text = it.data.url
//                    itemEpisodeDetailCreated.text = it.data.created
//                }
//            }
//        }
//    }
//
//    override fun initialize(){}
//
//    override fun setupObserver(){}
//
//    override fun setupRecycler(){}
//
//    override fun swipeFresh(){}
//}