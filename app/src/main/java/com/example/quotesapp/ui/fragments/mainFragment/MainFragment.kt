package com.example.quotesapp.ui.fragments.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quotesapp.R
import com.example.quotesapp.databinding.FragmentMainBinding
import com.example.quotesapp.ui.fragments.favouriteFragment.FavouriteFragment
import com.example.quotesapp.ui.fragments.homeFragment.HomeFragment
import com.example.quotesapp.ui.fragments.quotesFragment.QuotesFragment
import com.example.quotesapp.ui.fragments.settingFragment.SettingFragment

class MainFragment : Fragment() {
    private var _binding:FragmentMainBinding?=null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        replaceFragment(HomeFragment())

        binding?.bottomNav?.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home_menu -> replaceFragment(HomeFragment())
                R.id.favourite_menu -> replaceFragment(FavouriteFragment())
                R.id.setting_menu -> replaceFragment(SettingFragment())
            }
            true
        }
        return binding?.root
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frameLayout, fragment)?.commit()
    }




}