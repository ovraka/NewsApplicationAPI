package com.indocyber.api_service.usecase

import kotlinx.coroutines.flow.flow

class CategoryUseCase {
    operator fun invoke() = flow {
        val list = arrayListOf("Business", "Entertaiment", "General", "Health", "Science", "Sports",
            "Technology")
        emit(list)
    }
}