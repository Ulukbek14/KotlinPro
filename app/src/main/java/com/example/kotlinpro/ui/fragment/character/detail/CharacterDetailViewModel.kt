package com.example.kotlinpro.ui.fragment.character.detail

import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.dtos.CharacterDto
import com.example.kotlinpro.data.network.repository.CharacterRepository
import com.example.kotlinpro.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterDetailViewModel (
    private val repository: CharacterRepository) : BaseViewModel(){

    private val _characterState = MutableStateFlow<UIState<CharacterDto>>(UIState.Loading())
    val characterState: StateFlow<UIState<CharacterDto>> = _characterState

    fun fetchCharacter(id: Int) {
        _characterState.subscribeTo {
            repository.characterRepository(id)
        }
    }
}