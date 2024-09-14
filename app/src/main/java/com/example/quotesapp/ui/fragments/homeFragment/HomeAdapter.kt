package com.example.quotesapp.ui.fragments.homeFragment

import Quote
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.databinding.HomeItemBinding
import com.example.quotesapp.ui.fragments.quotesFragment.QuotesAdapter

class HomeAdapter(private var quotes: List<Quote>):RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    fun submitList(list: List<Quote>) {
        quotes = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = quotes.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = quotes[position]
        holder.binding.txtQuotesHeading.text = dataModel.text
        holder.binding.roundedImageView.setImageResource(R.drawable.wallpaper)


    }

    class ViewHolder(val binding: HomeItemBinding):RecyclerView.ViewHolder(binding.root)
}