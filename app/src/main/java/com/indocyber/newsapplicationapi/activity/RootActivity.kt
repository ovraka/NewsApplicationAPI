package com.indocyber.newsapplicationapi.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.indocyber.newsapplicationapi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root)
    }
}