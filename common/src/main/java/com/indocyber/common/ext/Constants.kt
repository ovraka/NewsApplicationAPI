package com.indocyber.common.ext

import com.indocyber.common.entity.source.Source

object Constants {
    const val DEFAULT_PAGE_SIZE = 10
    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "d03ddf78030a497d8e2b288e33dd1741"
    fun getImageFromUrl (source : Source) =
        "https://t0.gstatic.com/faviconV2?client=SOCIAL&type=FAVICON&fallback_opts=TYPE,SIZE,URL&url= ${source.url} &size=128"
}