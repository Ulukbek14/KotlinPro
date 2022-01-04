package com.example.kotlinpro.ui.fragment.character.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kotlinpro.R
import com.example.kotlinpro.common.base.BaseFragment
import com.example.kotlinpro.databinding.FragmentCharacterDetailBinding
import com.example.kotlinpro.state.UIState
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class CharacterDetailFragment : BaseFragment<CharacterDetailViewModel, FragmentCharacterDetailBinding>
        (R.layout.fragment_character_detail) {

    override val viewModel: CharacterDetailViewModel by viewModel()
    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(requireArguments()).id)
        getData()
    }

    private fun getData() = with(binding) {
        viewModel.characterState.subscribe {
            loaderCharacterDetail.isVisible = it is UIState.Loading
            groupCharacter.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    itemCharacterDetailName.text = it.data.name
                    itemCharacterDetailStatus.text = it.data.status
                    itemCharacterDetailSpecies.text = it.data.species
                    itemCharacterDetailGender.text = it.data.gender
                    Glide.with(itemCharacterDetailIv)
                        .load(it.data.image)
                        .into(itemCharacterDetailIv)
                    if (it.data.status == "Alive") {
                        itemCharacterDetailStatusDead.setBackgroundResource(R.drawable.alive)
                    } else if (it.data.status == "Dead") {
                        itemCharacterDetailStatusDead.setBackgroundResource(R.drawable.dead)
                    }
                }
            }
        }
    }

    override fun initialize(){}

    override fun setupObserver(){}

    override fun setupRecycler(){}

    override fun swipeFresh(){}
}