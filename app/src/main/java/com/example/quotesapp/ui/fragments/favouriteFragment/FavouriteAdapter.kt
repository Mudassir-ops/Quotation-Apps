package com.example.quotesapp.ui.fragments.favouriteFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.data.QuotesEntity
import com.example.quotesapp.databinding.FavouriteItemBinding
import com.example.quotesapp.databinding.QuotesItemBinding
import com.example.quotesapp.ui.fragments.quotesFragment.QuotesAdapter.ViewHolder

class FavouriteAdapter(private val favList: List<QuotesEntity>) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = favList[position]
        holder.binding.txtFavouriteQuotes.text = quote.quotesText
        holder.binding.txtFavouriteQuotesAuthor.text = quote.authorName
    }

    class ViewHolder(val binding: FavouriteItemBinding) : RecyclerView.ViewHolder(binding.root)
}