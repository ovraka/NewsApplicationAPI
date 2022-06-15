package com.indocyber.newsapplicationapi.app_fragment.everything_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.indocyber.newsapplicationapi.databinding.EverythingDetailBinding

class EverythingDetailFragment: Fragment() {

    val navDestination: EverythingDetailFragmentArgs by navArgs()
    lateinit var binding: EverythingDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EverythingDetailBinding.inflate(inflater, container, false)
        binding.webView.loadUrl(navDestination.webView)
        return binding.root
    }

}