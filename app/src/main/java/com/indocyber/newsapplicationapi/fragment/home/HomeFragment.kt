package com.indocyber.newsapplicationapi.fragment.home

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.indocyber.common.base.AppResponse
import com.indocyber.common.base.BaseFragment
import com.indocyber.common.entity.source.Source
import com.indocyber.newsapplicationapi.R
import com.indocyber.newsapplicationapi.databinding.LayoutHomeFragmentBinding
import com.indocyber.newsapplicationapi.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, LayoutHomeFragmentBinding>() {
    override val vm: HomeViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_home_fragment
    val categotyAdapter = CategoryAdapter(::selectCategory)
    val sourceAdapter = SourceAdapter(::selectSource)

    fun selectCategory(category : String) {
        if (category == vm.selectCategory.value) {
            vm.selectCategory.value = null
        }else{
            vm.selectCategory.value = category
        }
    }

    fun selectSource(source: Source) {
        vm.navigate(HomeFragmentDirections.homeToEverything(source))
    }

    override fun initBinding(binding: LayoutHomeFragmentBinding) {
        super.initBinding(binding)
        vm.selectCategory.observe(this) {
            vm.getSource()
        }
        binding.recyclerCategories.adapter = categotyAdapter
        vm.categoryData.observe(this) {
            categotyAdapter.sendData(it)
        }
        vm.getCategory()
        binding.search.addTextChangedListener {
            val data = vm.filter(it.toString())
            sourceAdapter.listData.submitList(data)
        }
        binding.recyclerSources.adapter = sourceAdapter
        observeResponseData(vm.sourceData, success = {
            sourceAdapter.sendData(it.sources)
            binding.loading.visibility = View.GONE
            binding.retryButton.visibility = View.GONE
        }, error = {
            binding.retryButton.visibility = View.VISIBLE
            binding.retryButton.setOnClickListener {
                vm.getSource()
            }
            binding.loading.visibility = View.GONE
        }, loading = {
            binding.retryButton.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        })
    }

}