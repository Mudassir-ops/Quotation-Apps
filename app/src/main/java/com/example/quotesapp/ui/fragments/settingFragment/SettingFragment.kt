package com.example.quotesapp.ui.fragments.settingFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quotesapp.R
import com.example.quotesapp.RateUsDialog
import com.example.quotesapp.databinding.FragmentSettingBinding
import com.example.quotesapp.ui.utils.feedBackWithEmail
import com.example.quotesapp.ui.utils.moreApps
import com.example.quotesapp.ui.utils.privacyPolicyUrl
import com.example.quotesapp.ui.utils.shareThisApp


class SettingFragment : Fragment() {
   private var _binding : FragmentSettingBinding?=null
    private val binding get() = _binding
    private var rateUsDialog:RateUsDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rateUsDialog = RateUsDialog(activity?:return)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun clickListener(){
        binding?.apply {
            val packageInfo = context?.packageManager?.getPackageInfo(context?.packageName!!, 0)
            val versionName = packageInfo?.versionName
            txtVersion.text = "Version: $versionName"

            icBack.setOnClickListener {
                if (findNavController().currentDestination?.id == R.id.mainFragment){
                    findNavController().navigate(R.id.mainFragment)
                }
            }
            icShare.setOnClickListener { activity?.shareThisApp() }
            icRateUs.setOnClickListener { rateUsDialog?.show()}
            icMoreApps.setOnClickListener { activity?.moreApps() }
            icPrivacyPolicy.setOnClickListener { activity?.privacyPolicyUrl() }
            icFeedback.setOnClickListener {
                activity?.feedBackWithEmail("Feedback", "Any Feedback", "shabirehtisham8@gmail.com")

            }
        }
    }

}