package com.indocyber.newsapplicationapi.app_fragment.everything_screen

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.indocyber.common.base.BaseFragment
import com.indocyber.newsapplicationapi.R
import com.indocyber.newsapplicationapi.databinding.LayoutEverythingFragmentBinding
import com.indocyber.newsapplicationapi.view_model.EverythingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EverythingFragment : BaseFragment<EverythingViewModel, LayoutEverythingFragmentBinding>() {
    override val vm: EverythingViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_everything_fragment
    val adapter = EverythingPagingAdapter(::selectEverything)
    val loadState = EverythingPagingStateAdapter()

    fun selectEverything(url: String) {
        vm.navigate(EverythingFragmentDirections.everythingToDetail(url))
    }

    val navFragmentArgs: EverythingFragmentArgs by navArgs()
    override fun initBinding(binding: LayoutEverythingFragmentBinding) {
        super.initBinding(binding)
        binding.recycler.adapter = adapter.withLoadStateFooter(loadState)
        vm.getEverything(navFragmentArgs.source)
        vm.pagingData.observe(this) {
            CoroutineScope(Dispatchers.Main).launch {
                adapter.submitData(it)
            }
        }
        binding.textBack.setOnClickListener {
            vm.popBackStack()
        }
        binding.search.addTextChangedListener {
            vm.searchText.value = it.toString()
            vm.getEverything(navFragmentArgs.source)
        }
        adapter.addLoadStateListener {
            if (it.append is LoadState.Error || it.refresh is LoadState.Error) {
                binding.recycler.visibility = View.GONE
                binding.loading.visibility = View.GONE
                binding.retryButton.visibility = View.VISIBLE
                binding.retryButton.setOnClickListener {
                    vm.getEverything(navFragmentArgs.source)
                }
            } else if (it.refresh is LoadState.Loading) {
                binding.recycler.visibility = View.GONE
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.recycler.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
                binding.loading.visibility = View.GONE
            }
        }

    }
}