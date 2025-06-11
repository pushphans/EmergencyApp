package com.example.college.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.college.R
import com.example.college.databinding.FragmentTowcarBinding


class TowcarFragment : Fragment(R.layout.fragment_towcar) {

    private var _binding : FragmentTowcarBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTowcarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpWebView()
        initializeWebview()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.homeFragment, false)
        }


    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView(){
        binding.towcarWebview.settings.javaScriptEnabled = true
        binding.towcarWebview.settings.setGeolocationEnabled(true)
        binding.towcarWebview.webChromeClient = object : WebChromeClient(){
            override fun onGeolocationPermissionsShowPrompt(
                origin: String?,
                callback: GeolocationPermissions.Callback?
            ) {
                callback?.invoke(origin, true, false)

            }
        }
        binding.towcarWebview.webViewClient = WebViewClient()
    }

    private fun initializeWebview(){
        binding.towcarWebview.loadUrl("https://www.google.com/search?q=towing+service+near+me")
    }




    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = "Tow Car Services Near Me"
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}