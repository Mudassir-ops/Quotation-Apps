package com.example.quotesapp.ui.fragments.quotesFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapp.ui.utils.KEY_QUOTES
import com.example.quotesapp.databinding.FragmentQuotesBinding
import com.example.quotesapp.ui.utils.parcelableArrayList
import com.example.quotesapp.ui.json.Quotes
import com.example.quotesapp.ui.utils.KEY_IMAGE_RESOURCE

class QuotesFragment : Fragment() {
    private var _binding: FragmentQuotesBinding? = null
    private val binding get() = _binding
    private var quotesList: ArrayList<Quotes>? = null
    private  var quotesAdapter: QuotesAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quotesList = arguments?.parcelableArrayList<Quotes>(KEY_QUOTES)
        quotesAdapter = quotesList?.let {
            QuotesAdapter(quotes = it,context?:return)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageResource = arguments?.getInt(KEY_IMAGE_RESOURCE)
        imageResource?.let {
            binding?.quotesImageView?.setImageResource(it)
        }
        binding?.icBack?.setOnClickListener {
            findNavController().navigateUp()
        }
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding?.quotesDetailRecyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = quotesAdapter
        }
    }

}