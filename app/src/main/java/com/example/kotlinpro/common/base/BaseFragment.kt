package com.example.kotlinpro.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.kotlinpro.state.UIState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes Id: Int) : Fragment(Id) {

    protected abstract val binding: ViewBinding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserver()
        setupRecycler()
        swipeFresh()
    }

    @InternalCoroutinesApi
    protected fun <T> StateFlow<UIState<T>>.subscribe(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: (UIState<T>) -> Unit) {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state){
                this@subscribe.collect{
                    action(it)
                }
            }
        }
    }

    abstract fun initialize()

    abstract fun setupObserver()

    abstract fun setupRecycler()

    abstract fun swipeFresh()
}