package com.example.kotlinpro.ui.fragment.character.detail

import androidx.lifecycle.MutableLiveData
import com.example.kotlinpro.common.base.BaseViewModel
import com.example.kotlinpro.data.network.dtos.CharacterDto
import com.example.kotlinpro.data.network.repository.CharacterRepository
import com.example.kotlinpro.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository) : BaseViewModel(){

    private val _characterState = MutableStateFlow<UIState<CharacterDto>>(UIState.Loading())
    val characterState: StateFlow<UIState<CharacterDto>> = _characterState

    fun fetchCharacter(id: Int) {
        _characterState.subscribeTo {
            repository.characterRepository(id)
        }
    }
}