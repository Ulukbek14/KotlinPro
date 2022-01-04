package com.example.kotlinpro.ui.fragment.location.detail

import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.dtos.LocationDto
import com.example.kotlinpro.data.network.repository.LocationRepository
import com.example.kotlinpro.state.UIState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationDetailViewModel (
    private val repository: LocationRepository) : BaseViewModel() {

    private val _locationState = MutableStateFlow<UIState<LocationDto>>(UIState.Loading())
    val locationState: StateFlow<UIState<LocationDto>> = _locationState

    fun fetchLocation(id: Int) {
        _locationState.subscribeTo {
            repository.locationRepository(id)
        }
    }
}