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
import com.example.quotesapp.ui.utils.KEY_QUOTES
import com.example.quotesapp.R
import com.example.quotesapp.databinding.FragmentHomeBinding
import com.example.quotesapp.ui.json.Categories
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("fragmentLifecycle", "onCreate: onCreate")

        homeAdapter = HomeAdapter(arrayListOf(),
            callback = {
                Log.d("fragmentLifecycle", "Callback triggered with data: $it")
                Log.d("fragmentLifecycle", "Current destination ID: ${findNavController().currentDestination?.id}")

                if (findNavController().currentDestination?.id == R.id.homeFragment) {
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(KEY_QUOTES, it)
                    findNavController().navigate(R.id.quotesFragment, bundle)
                }
            })
        observingQuotes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("fragmentLifecycle", "onViewCreated: onCreateView")

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("fragmentLifecycle", "onViewCreated: onViewCreated")
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
        homeAdapter.submitList(dataset)
    }



}