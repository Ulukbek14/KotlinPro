package com.example.kotlinpro.ui.fragment.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.repository.CharacterRepository
import javax.inject.Inject

class CharacterViewModel (
    private val repository: CharacterRepository) : BaseViewModel() {
    fun fetchCharacters() = repository.charactersRepository().cachedIn(viewModelScope)
}