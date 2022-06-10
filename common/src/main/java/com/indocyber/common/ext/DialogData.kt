package com.indocyber.common.ext

data class DialogData (
    val title: String,
    val message: String,
    val positiveButton: Pair<String, () -> Unit>?,
    val negativeButton: Pair<String, () -> Unit>?
)