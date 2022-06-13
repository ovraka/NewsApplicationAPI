package com.indocyber.api_service.usecase

import kotlinx.coroutines.flow.flow

class CategoryUseCase {
    operator fun invoke() = flow {
        val list = arrayListOf("Business", "Entertainment", "General", "Health", "Science", "Sports",
            "Technology")
        emit(list)
    }
}