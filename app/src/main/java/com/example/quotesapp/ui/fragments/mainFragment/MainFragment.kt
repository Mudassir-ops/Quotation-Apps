package com.example.quotesapp.ui.fragments.mainFragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.quotesapp.R
import com.example.quotesapp.databinding.FragmentMainBinding
import com.example.quotesapp.ui.fragments.favouriteFragment.FavouriteFragment
import com.example.quotesapp.ui.fragments.homeFragment.HomeFragment
import com.example.quotesapp.ui.fragments.settingFragment.SettingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding:FragmentMainBinding?=null
    private val binding get() = _binding
    private var currentFragment: Fragment = HomeFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        binding?.bottomNav?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(0, 0, 0, systemBars.bottom)
                insets
            }
        }

        binding?.bottomNav?.apply {
            setPadding(0, 0, 0, -8)
            elevation = 0f
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        replaceFragment(HomeFragment())

        binding?.bottomNav?.setPadding(0, 0, 0, 0)

        binding?.bottomNav?.setOnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.home_menu -> HomeFragment()
                R.id.favourite_menu -> FavouriteFragment()
                R.id.setting_menu -> SettingFragment()
                else -> null
            }

            if (fragment != null) {
                replaceFragment(fragment)
                currentFragment = fragment
            }
            true
        }
        return binding?.root
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (currentFragment is HomeFragment) {
                showExitDialog()
            } else {
                replaceFragment(HomeFragment())
                currentFragment = HomeFragment()
            }
        }
    }

    private fun showExitDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ -> requireActivity().finish() }
            .setNegativeButton("No", null)
            .show()
    }


}