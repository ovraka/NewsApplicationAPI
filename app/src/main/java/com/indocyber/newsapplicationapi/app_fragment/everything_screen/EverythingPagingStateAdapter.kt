package com.indocyber.newsapplicationapi.app_fragment.everything_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.indocyber.newsapplicationapi.databinding.LayoutPagingStateBinding

class EverythingPagingStateAdapter() :
    LoadStateAdapter<EverythingPagingStateAdapter.EverythingPagingStateViewHolder>() {

    inner class EverythingPagingStateViewHolder(val binding: LayoutPagingStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            when (loadState) {
                is LoadState.Error -> {
                    binding.error.visibility = View.GONE
                    binding.loading.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    binding.error.visibility = View.GONE
                    binding.loading.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    binding.error.visibility = View.GONE
                    binding.loading.visibility = View.GONE
                }
            }
        }
    }

    override fun onBindViewHolder(holder: EverythingPagingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): EverythingPagingStateViewHolder =
        EverythingPagingStateViewHolder(
            LayoutPagingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            this.bind(loadState)
        }
}