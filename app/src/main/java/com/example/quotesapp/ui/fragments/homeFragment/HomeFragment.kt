package com.example.quotesapp.ui.fragments.homeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapp.R
import com.example.quotesapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() =  _binding
    private val viewModel:HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeRepository: HomeRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeAdapter = HomeAdapter(emptyList())
        homeRepository = HomeRepository(context?:requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        GlobalScope.launch(Dispatchers.Main) {
            val quotesData = homeRepository.fetchQuotesData()
            if (quotesData != null) {
                quotesData.lifeQuotes?.let { homeAdapter.submitList(it) }
            }
        }

        /*viewModel.quotesData.observe(viewLifecycleOwner) { quotesData ->
            quotesData?.let {
                val allQuotes = listOf(
                    it.motivationalQuotes ?: emptyList(),
                    it.inspirationalQuotes ?: emptyList(),
                    it.loveQuotes ?: emptyList(),
                    it.friendshipQuotes ?: emptyList(),
                    it.successQuotes ?: emptyList(),
                    it.lifeQuotes ?: emptyList(),
                    it.happinessQuotes ?: emptyList(),
                    it.leadershipQuotes ?: emptyList(),
                    it.wisdomQuotes ?: emptyList(),
                    it.positiveThinkingQuotes ?: emptyList(),
                    it.courageQuotes ?: emptyList(),
                    it.familyQuotes ?: emptyList(),
                    it.selfLoveQuotes ?: emptyList(),
                    it.timeManagementQuotes ?: emptyList(),
                    it.gratitudeQuotes ?: emptyList()
                ).flatten()

                homeAdapter.submitList(allQuotes.map { quote ->
                    HomeDataModel(quote.quotes ?: "", R.drawable.wallpaper)
                })
            }
        }*/
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

}