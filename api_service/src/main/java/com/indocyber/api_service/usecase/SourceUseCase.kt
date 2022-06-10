package com.indocyber.api_service.usecase

import com.indocyber.api_service.service.SourceService
import com.indocyber.common.base.AppResponse
import com.indocyber.common.entity.source.SourceResponse
import kotlinx.coroutines.flow.flow

class SourceUseCase(val sourceService: SourceService) {
    operator fun invoke(category: String? = null) = flow<AppResponse<SourceResponse>> {
        try {
            emit(AppResponse.loading())
            val result = sourceService.getSources(category = category)
            result.body()?.let {
                emit(AppResponse.success(it))
            } ?: run {
                emit(AppResponse.failure(Exception("Null Data")))
            }

        } catch (e: Exception) {
            emit(AppResponse.failure(e))
        }
    }
}