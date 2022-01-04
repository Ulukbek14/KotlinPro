package com.example.kotlinpro.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlinpro.common.base.BaseRepository
import com.example.kotlinpro.data.network.apiservise.EpisodeApiService
import com.example.kotlinpro.data.network.dtos.EpisodeDto
import com.example.kotlinpro.data.network.pagingsources.EpisodePagingSource
import kotlinx.coroutines.flow.Flow

class EpisodeRepository (
    private val service: EpisodeApiService) : BaseRepository() {

    fun episodeRepository(): Flow<PagingData<EpisodeDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).flow
    }

    fun episodeRepository(id: Int) = doRequest {
        service.fetchEpisodeApiService(id)
    }
}