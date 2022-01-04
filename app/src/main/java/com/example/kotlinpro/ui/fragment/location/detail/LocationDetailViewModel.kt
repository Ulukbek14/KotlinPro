package com.example.kotlinpro.ui.fragment.location.detail

import androidx.lifecycle.MutableLiveData
import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.dtos.LocationDto
import com.example.kotlinpro.data.network.repository.LocationRepository
import com.example.kotlinpro.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val repository: LocationRepository) : BaseViewModel() {

    private val _locationState = MutableStateFlow<UIState<LocationDto>>(UIState.Loading())
    val locationState: StateFlow<UIState<LocationDto>> = _locationState


    fun fetchLocation(id: Int) {
        _locationState.subscribeTo {
            repository.locationRepository(id)
        }
    }
}