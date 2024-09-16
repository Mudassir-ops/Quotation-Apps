package com.example.quotesapp.ui.fragments.homeFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.databinding.HomeItemBinding
import com.example.quotesapp.ui.json.Categories
import com.example.quotesapp.ui.json.Quotes

class HomeAdapter(private var quotes: ArrayList<Categories>,
                  private val callback:(ArrayList<Quotes>)->Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<Categories>) {
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
        holder.binding.txtQuotesHeading.text = dataModel.categoryName.toString()
        holder.binding.roundedImageView.setImageResource(R.drawable.wallpaper)
        holder.itemView.setOnClickListener {
            dataModel.quotes?.let { it1 -> callback.invoke(it1) }
        }


    }

    class ViewHolder(val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root)
}