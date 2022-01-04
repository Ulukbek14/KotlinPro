package com.example.kotlinpro.ui.fragment.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository) : BaseViewModel() {

    fun fetchCharacters() = repository.charactersRepository().cachedIn(viewModelScope)
}