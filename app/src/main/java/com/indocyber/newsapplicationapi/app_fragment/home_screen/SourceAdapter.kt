package com.indocyber.newsapplicationapi.app_fragment.home_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.indocyber.common.entity.source.Source
import com.indocyber.common.ext.Constants
import com.indocyber.common.ext.loadImageFromUrl
import com.indocyber.newsapplicationapi.databinding.LayoutSourceItemBinding

class SourceAdapter(val selectSource: (Source) -> Unit) : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    val listData = AsyncListDiffer<Source>(this, differ)

    inner class SourceViewHolder(val binding: LayoutSourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Source) {
            binding.root.setOnClickListener {
                selectSource(source)
            }
            binding.sourceImage.loadImageFromUrl(Constants.getImageFromUrl(source))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder =
        SourceViewHolder(
            LayoutSourceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.binding.data = listData.currentList[position]
        holder.bind(listData.currentList[position])
    }

    override fun getItemCount(): Int {
        return listData.currentList.size
    }

    fun sendData(sources : List<Source>) {
        listData.submitList(sources)
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<Source>() {
            override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem == newItem
            }

        }
    }
}