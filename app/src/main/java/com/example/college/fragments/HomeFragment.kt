package com.example.college.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.college.R
import com.example.college.databinding.FragmentHomeBinding
import com.example.college.db.ContactViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewmodel : ContactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if(viewmodel.isTrue == true) {
            lifecycleScope.launch {
                delay(0)
                binding.image.visibility = View.VISIBLE
                binding.entryText.visibility = View.VISIBLE

                delay(2000)
                binding.showLinearLayout.visibility = View.VISIBLE
                binding.image.visibility = View.GONE
                binding.entryText.visibility = View.GONE

                viewmodel.isTrue = false
            }
        }
        else{
            binding.showLinearLayout.visibility = View.VISIBLE
            binding.image.visibility = View.GONE
            binding.entryText.visibility = View.GONE
        }

        binding.fastDial.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_showcontactFragment)
        }

        binding.sos.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sosFragment)
        }

        binding.shareLocation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sharelocationFragment)
        }

        binding.mechanicMap.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_blankFragment)
        }

        binding.hospitalServices.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_hospitalmapFragment)
        }

        binding.towServices.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_towcarFragment)
        }

        binding.fuelServices.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fuelFragment)
        }

        binding.firstAidVideo.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToVideoFragment("gn6xt1ca8A0")
            findNavController().navigate(action)
        }

        binding.tyreRepairVideo.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToVideoFragment("_ocVkYAAaVg")
            findNavController().navigate(action)
        }

        binding.fireExtinguisherVideo.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToVideoFragment("GVBamXXVD30")
            findNavController().navigate(action)
        }





    }



    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = "Dashboard"
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}