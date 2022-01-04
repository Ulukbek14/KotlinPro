package com.example.kotlinpro.ui.fragment.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.repository.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    fun fetchEpisodes() = repository.episodeRepository().cachedIn(viewModelScope)

}