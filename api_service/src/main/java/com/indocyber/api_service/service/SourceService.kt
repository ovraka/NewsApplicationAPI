package com.indocyber.api_service.service

import com.indocyber.common.entity.source.SourceResponse
import com.indocyber.common.ext.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SourceService {
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("category") category: String? = null
    ): Response<SourceResponse>
}