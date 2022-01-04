package com.example.kotlinpro.ui.fragment.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.repository.LocationRepository

class LocationViewModel (
    private val repository: LocationRepository) : BaseViewModel() {
    fun fetchLocations() = repository.locationRepository().cachedIn(viewModelScope)
}