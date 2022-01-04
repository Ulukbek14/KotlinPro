package com.example.kotlinpro.data.network.pagingsources

import com.example.kotlinpro.common.base.BasePagingSource
import com.example.kotlinpro.data.network.apiservise.EpisodeApiService
import com.example.kotlinpro.data.network.dtos.EpisodeDto

class EpisodePagingSource(
    private val service: EpisodeApiService) : BasePagingSource<EpisodeDto>({ position ->
    service.fetchEpisodesApiService(position)
})
