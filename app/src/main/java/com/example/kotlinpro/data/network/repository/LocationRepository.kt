package com.example.kotlinpro.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlinpro.common.base.BaseRepository
import com.example.kotlinpro.data.network.apiservise.LocationApiService
import com.example.kotlinpro.data.network.dtos.LocationDto
import com.example.kotlinpro.data.network.pagingsources.LocationPagingSource
import kotlinx.coroutines.flow.Flow

class LocationRepository (
    private val service: LocationApiService) : BaseRepository() {

    fun locationRepository(): Flow<PagingData<LocationDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).flow
    }


    fun locationRepository(id: Int) = doRequest {
        service.fetchLocationApiService(id)
    }
}