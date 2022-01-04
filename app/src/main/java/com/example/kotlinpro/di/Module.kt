package com.example.kotlinpro.di

import com.example.kotlinpro.data.network.RetrofitClient
import com.example.kotlinpro.data.network.repository.CharacterRepository
import com.example.kotlinpro.data.network.repository.EpisodeRepository
import com.example.kotlinpro.data.network.repository.LocationRepository
import com.example.kotlinpro.ui.fragment.character.CharacterViewModel
import com.example.kotlinpro.ui.fragment.character.detail.CharacterDetailViewModel
import com.example.kotlinpro.ui.fragment.episode.EpisodeViewModel
import com.example.kotlinpro.ui.fragment.episode.detail.EpisodeDetailViewModel
import com.example.kotlinpro.ui.fragment.location.LocationViewModel
import com.example.kotlinpro.ui.fragment.location.detail.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideCharacterRetrofitClient() }
    single { get<RetrofitClient>().provideEpisodeRetrofitClient() }
    single { get<RetrofitClient>().provideLocationRetrofitClient() }
}
val repositoriesModel = module {
    factory { CharacterRepository(get ()) }
    factory { EpisodeRepository(get ()) }
    factory { LocationRepository(get()) }
}
val viewModelsModule = module {
    viewModel{ CharacterViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { EpisodeDetailViewModel(get()) }
    viewModel { LocationDetailViewModel(get()) }

}