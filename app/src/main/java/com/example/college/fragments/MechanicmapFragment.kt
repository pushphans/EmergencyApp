package com.example.college.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.college.R
import com.example.college.databinding.FragmentMechanicmapBinding

class MechanicmapFragment : Fragment(R.layout.fragment_mechanicmap) {


    private var _binding: FragmentMechanicmapBinding? = null
    private val binding get() = _binding!!

    private val fineLocation = android.Manifest.permission.ACCESS_FINE_LOCATION
    private val coarseLocation = android.Manifest.permission.ACCESS_COARSE_LOCATION
    private val permissionsToRequest = mutableListOf(fineLocation, coarseLocation)

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){permission ->
        val allGranted = permission.all {entry ->
            entry.value == true
        }

        if(allGranted == true){
            if(isLocationEnabled() == true){
                setUpWebView()
                initializeWebview()
            }else{
                Toast.makeText(requireContext(), "Enable location from setting and restart the app", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(requireContext(), "Some permissions are missing", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMechanicmapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermissions()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.homeFragment, false)
        }

    }

    private fun checkPermissions() {
        val missingPermissions = permissionsToRequest.filter {unGrantedPermissions ->
            ContextCompat.checkSelfPermission(requireContext(), unGrantedPermissions) != PackageManager.PERMISSION_GRANTED
        }

        if(missingPermissions.isNotEmpty()){
            permissionLauncher.launch(missingPermissions.toTypedArray())

        }else{
            if(isLocationEnabled() == true){
                setUpWebView()
                initializeWebview()

            }else{
                Toast.makeText(requireContext(), "Enable location from setting and restart the app", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = requireContext().getSystemService(LocationManager::class.java)
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() {
        binding.mechanicMapWebview.settings.setGeolocationEnabled(true)
        binding.mechanicMapWebview.settings.javaScriptEnabled = true
        binding.mechanicMapWebview.webChromeClient = object : WebChromeClient() {
            override fun onGeolocationPermissionsShowPrompt(
                origin: String?,
                callback: GeolocationPermissions.Callback?
            ) {
                callback?.invoke(origin, true, false)

            }
        }
        binding.mechanicMapWebview.webViewClient = WebViewClient()
    }

    private fun initializeWebview() {
        binding.mechanicMapWebview.loadUrl("https://www.google.com/search?q=mechanics+near+me")
    }


    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = "Mechanics Near Me"
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}