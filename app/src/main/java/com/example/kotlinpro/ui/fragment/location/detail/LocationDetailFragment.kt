package com.example.kotlinpro.ui.fragment.location.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlinpro.R
import com.example.kotlinpro.common.base.BaseFragment
import com.example.kotlinpro.databinding.FragmentLocationDetailBinding
import com.example.kotlinpro.state.UIState
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class LocationDetailFragment : BaseFragment<LocationDetailViewModel, FragmentLocationDetailBinding>
        (R.layout.fragment_location_detail) {

    override val viewModel: LocationDetailViewModel by viewModel()
    override val binding by viewBinding(FragmentLocationDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(requireArguments()).id)
        getData()
    }

    private fun getData() = with(binding) {
        viewModel.locationState.subscribe {
            loaderLocationDetail.isVisible = it is UIState.Loading
            groupLocation.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    itemLocationDetailName.text = it.data.name
                    itemLocationDetailUrl.text = it.data.url
                    itemLocationDetailCreated.text = it.data.created
                }
            }
        }
    }

    override fun initialize(){}

    override fun setupObserver(){}

    override fun setupRecycler(){}

    override fun swipeFresh(){}
}