package com.indocyber.newsapplicationapi.app_fragment.home_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.indocyber.newsapplicationapi.databinding.LayoutCategoryItemBinding

class CategoryAdapter(val selectCategory: (String) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val listData = AsyncListDiffer<String>(this, differ)

    inner class CategoryViewHolder(val binding: LayoutCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.categoryName.setOnClickListener {
                selectCategory(data)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            LayoutCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listData.currentList[position])
        holder.binding.data = listData.currentList[position]
    }

    override fun getItemCount(): Int {
        return listData.currentList.size
    }

    fun sendData(categories: List<String>) {
        listData.submitList(categories)
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return true
            }

        }
    }
}