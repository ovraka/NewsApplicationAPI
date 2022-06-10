package com.indocyber.newsapplicationapi.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.indocyber.api_service.usecase.CategoryUseCase
import com.indocyber.api_service.usecase.SourceUseCase
import com.indocyber.common.base.AppResponse
import com.indocyber.common.base.BaseViewModel
import com.indocyber.common.entity.source.Source
import com.indocyber.common.entity.source.SourceResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    val categoryUseCase: CategoryUseCase,
    val sourceUseCase: SourceUseCase
) : BaseViewModel(application) {
    val categoryData = MutableLiveData<List<String>>()
    val sourceData = MutableLiveData<AppResponse<SourceResponse>>()
    val selectCategory = MutableLiveData<String>()

    init {
        getCategory()
        getSource()
    }

    fun getCategory() {
        viewModelScope.launch {
            categoryUseCase.invoke().collect {
                categoryData.postValue(it)
            }
        }
    }

    fun getSource() {
        viewModelScope.launch {
            sourceUseCase.invoke(selectCategory.value).collect {
                sourceData.postValue(it)
            }
        }

    }

    fun filter(q: String): List<Source> = sourceData.value?.let {
        if (it is AppResponse.AppResponseSuccess) {
            it.data?.sources.orEmpty().filter {
                it.name.contains(q, true)
            }
        } else {
            arrayListOf()
        }
    } ?: run {
        arrayListOf()
    }
}