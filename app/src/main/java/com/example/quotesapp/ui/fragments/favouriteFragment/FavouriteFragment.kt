package com.example.quotesapp.ui.fragments.favouriteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quotesapp.R
import com.example.quotesapp.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    private var _binding:FragmentFavouriteBinding?= null
    private val binding get() =  _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        return binding?.root
    }
}