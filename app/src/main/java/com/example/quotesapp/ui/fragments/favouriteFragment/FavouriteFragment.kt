package com.example.quotesapp.ui.fragments.favouriteFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapp.data.QuotesDao
import com.example.quotesapp.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private var _binding:FragmentFavouriteBinding?= null
    private val binding get() =  _binding
    @Inject
    lateinit var quotesDao: QuotesDao // Ensure this is injected by Hilt
    private val viewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeFavouriteQuotes()
    }

    private fun setupRecyclerView() {
        binding?.favRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun observeFavouriteQuotes() {
        viewModel.favouriteQuotes.observe(viewLifecycleOwner) { favList ->
            if (favList.isNullOrEmpty()) {
                binding?.txtFavourite?.visibility = View.VISIBLE
                binding?.favRecyclerView?.visibility = View.GONE
            } else {
                binding?.txtFavourite?.visibility = View.GONE
                binding?.favRecyclerView?.visibility = View.VISIBLE

                // Reverse the list here
                val reversedList = favList.reversed()

                val adapter = FavouriteAdapter(reversedList,context?:return@observe)
                binding?.favRecyclerView?.adapter = adapter
            }
        }
    }

}