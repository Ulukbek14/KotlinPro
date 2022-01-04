package com.example.kotlinpro.data.network.pagingsources

import com.example.kotlinpro.common.base.BasePagingSource
import com.example.kotlinpro.data.network.apiservise.LocationApiService
import com.example.kotlinpro.data.network.dtos.LocationDto

class LocationPagingSource(
    private val service: LocationApiService) : BasePagingSource<LocationDto>({ position ->
    service.fetchLocationsApiService(position)
})