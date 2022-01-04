package com.example.kotlinpro.ui.fragment.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.repository.EpisodeRepository
import javax.inject.Inject

class EpisodeViewModel (
    private val repository: EpisodeRepository) : BaseViewModel() {
    fun fetchEpisodes() = repository.episodeRepository().cachedIn(viewModelScope)
}