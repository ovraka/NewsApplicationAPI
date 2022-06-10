package com.indocyber.newsapplicationapi.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.indocyber.api_service.usecase.EverythingUseCase
import com.indocyber.common.base.BaseViewModel
import com.indocyber.common.entity.everything.Article
import com.indocyber.common.entity.source.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(
    application: Application,
    val everythingUseCase: EverythingUseCase
) : BaseViewModel(application) {

    val searchText = MutableLiveData<String>()
    val pagingData = MutableLiveData<PagingData<Article>>()

    fun getEverything(source: Source){
        viewModelScope.launch {
            everythingUseCase(q = searchText.value, sources = listOf(source.id)).collect {
                pagingData.postValue(it)
            }
        }
    }
}