package com.example.quotesapp.ui.fragments.settingFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quotesapp.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {
   private var _binding : FragmentSettingBinding?=null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater,container,false)
        return binding?.root
    }

}