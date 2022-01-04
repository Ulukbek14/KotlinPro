package com.example.kotlinpro.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlinpro.common.base.BaseRepository
import com.example.kotlinpro.data.network.apiservise.CharacterApiService
import com.example.kotlinpro.data.network.dtos.CharacterDto
import com.example.kotlinpro.data.network.pagingsources.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService) : BaseRepository() {

    fun charactersRepository(): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).flow
    }

    fun characterRepository(id: Int) = doRequest {
        service.fetchCharacterApiService(id)
    }
}