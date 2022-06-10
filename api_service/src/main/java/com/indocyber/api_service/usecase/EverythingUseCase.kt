package com.indocyber.api_service.usecase

import com.indocyber.api_service.paging.EverythingPager
import com.indocyber.api_service.service.EverythingService
import com.indocyber.common.ext.Constants

class EverythingUseCase(val everythingService: EverythingService) {
    operator fun invoke(q: String?, sources: List<String>) =
        EverythingPager.createPager(Constants.DEFAULT_PAGE_SIZE, everythingService, q, sources).flow
}