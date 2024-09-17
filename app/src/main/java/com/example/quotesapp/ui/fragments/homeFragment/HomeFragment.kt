package com.example.quotesapp.ui.fragments.homeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapp.R
import com.example.quotesapp.databinding.FragmentHomeBinding
import com.example.quotesapp.ui.json.Categories
import com.example.quotesapp.ui.utils.KEY_IMAGE_RESOURCE
import com.example.quotesapp.ui.utils.KEY_QUOTES
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeAdapter = HomeAdapter(arrayListOf(),
            callback = {quotesList, imageResource ->

                if (findNavController().currentDestination?.id == R.id.mainFragment) {
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(KEY_QUOTES, quotesList)
                    bundle.putInt(KEY_IMAGE_RESOURCE,imageResource)

                    findNavController().navigate(R.id.quotesFragment, bundle)
                }
            })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observingQuotes()
        setupRecyclerView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding?.quotesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
    }

    private fun observingQuotes() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.quotesData.collect { uiState ->
                    uiState?.let {
                        updateAdapterData(it.categories)
                    }
                }
            }
        }
    }

    private fun updateAdapterData(dataset: ArrayList<Categories>) {
        dataset.forEach { category ->
            category.imageResource = getCategoryImage(category.categoryName.toString())
        }
        homeAdapter.submitList(dataset)
    }

    private fun getCategoryImage(categoryName: String): Int {
        return when (categoryName) {
            "Motivational Quotes" -> R.drawable.wallpaper
            "Inspirational Quotes" -> R.drawable.splash_image_quotes
            "Love Quotes" -> R.drawable.splash_image_two
            "Friendship Quotes" -> R.drawable.wallpaper
            "Success Quotes" -> R.drawable.splash_image_quotes
            "Life Quotes" -> R.drawable.splash_image_two
            "Happiness Quotes" -> R.drawable.wallpaper
            "Leadership Quotes" -> R.drawable.splash_image_quotes
            "Wisdom Quotes" -> R.drawable.splash_image_two
            "Positive Thinking Quotes" -> R.drawable.wallpaper
            "Courage Quotes" -> R.drawable.splash_image_quotes
            "Family Quotes" -> R.drawable.splash_image_two
            "Self Love Quotes" -> R.drawable.wallpaper
            "Time Management Quotes" -> R.drawable.splash_image_quotes
            "Gratitude Quotes" -> R.drawable.splash_image_two
            else -> R.drawable.wallpaper
        }
    }



}