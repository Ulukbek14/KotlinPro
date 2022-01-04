package com.example.kotlinpro.ui.fragment.episode.detail

import androidx.lifecycle.MutableLiveData
import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.dtos.EpisodeDto
import com.example.kotlinpro.data.network.repository.EpisodeRepository
import com.example.kotlinpro.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val repository: EpisodeRepository) : BaseViewModel() {

    private val _episodeState = MutableStateFlow<UIState<EpisodeDto>>(UIState.Loading())
    val episodeState: StateFlow<UIState<EpisodeDto>> = _episodeState

    fun fetchEpisode(id: Int) {
        _episodeState.subscribeTo {
            repository.episodeRepository(id)
        }
    }
}