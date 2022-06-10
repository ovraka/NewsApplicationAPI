package com.indocyber.common.entity.source


import com.google.gson.annotations.SerializedName

data class SourceResponse(
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("status")
    val status: String
)