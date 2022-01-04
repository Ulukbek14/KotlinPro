package com.example.kotlinpro.ui.fragment.character

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlinpro.R
import com.example.kotlinpro.common.base.BaseFragment
import com.example.kotlinpro.databinding.FragmentCharacterBinding
import com.example.kotlinpro.ui.adapter.CharacterAdapter
import com.example.kotlinpro.ui.adapter.load.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter = CharacterAdapter(this::setOnItemClickListener)

    override fun initialize() = with(binding) {
        characterAdapter.addLoadStateListener { loadStates ->
            rvCharacter.isVisible = loadStates.refresh is LoadState.NotLoading
            characterProgressBar.isVisible = loadStates.refresh is LoadState.Loading
            characterSwiperefreshLayout.isRefreshing = false
            viewModel.fetchCharacters()
        }
    }

    override fun setupObserver() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest { data ->
                characterAdapter.submitData(data)
            }
        }
    }

    override fun setupRecycler() = with(binding){
        rvCharacter.layoutManager = LinearLayoutManager(context)
        rvCharacter.adapter = characterAdapter.withLoadStateFooter(
            LoadStateAdapter { characterAdapter.retry() })
    }

    override fun swipeFresh() {
        binding.characterSwiperefreshLayout.setOnRefreshListener {
            characterAdapter.refresh()
        }
    }

    private fun setOnItemClickListener(name: String, id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                label = "${"Character"} $name", id = id
            )
        )
    }
}