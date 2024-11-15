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
import com.example.quotesapp.ui.utils.KEY_CATEGORY_NAME
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
            callback = {quotesList, imageResource,categoryName ->

                if (findNavController().currentDestination?.id == R.id.mainFragment) {
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(KEY_QUOTES, quotesList)
                    bundle.putInt(KEY_IMAGE_RESOURCE,imageResource)
                    bundle.putString(KEY_CATEGORY_NAME, categoryName)
                    Log.e("quotes", "onCreate: send quotesList $quotesList", )

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
            "Motivational Quotes" -> R.drawable.artboard_11
            "Inspirational Quotes" -> R.drawable.artboard_7
            "Love Quotes" -> R.drawable.artboard_10
            "Friendship Quotes" -> R.drawable.artboard_3
            "Success Quotes" -> R.drawable.artboard_14
            "Life Quotes" -> R.drawable.artboard_9
            "Happiness Quotes" -> R.drawable.artboard_5
            "Leadership Quotes" -> R.drawable.artboard_8
            "Wisdom Quotes" -> R.drawable.artboard_16
            "Positive Thinking Quotes" -> R.drawable.artboard_12
            "Courage Quotes" -> R.drawable.artboard_1
            "Family Quotes" -> R.drawable.artboard_2
            "Self Love Quotes" -> R.drawable.artboard_13
            "Time Management Quotes" -> R.drawable.artboard_15
            "Gratitude Quotes" -> R.drawable.artboard_4
            else -> R.drawable.wallpaper
        }
    }



}