package com.example.kotlinpro.data.network.pagingsources

import com.example.kotlinpro.common.base.BasePagingSource
import com.example.kotlinpro.data.network.apiservise.CharacterApiService
import com.example.kotlinpro.data.network.dtos.CharacterDto

class CharacterPagingSource(
    private val service: CharacterApiService) : BasePagingSource<CharacterDto>({ position ->
    service.fetchCharactersApiService(position)
})