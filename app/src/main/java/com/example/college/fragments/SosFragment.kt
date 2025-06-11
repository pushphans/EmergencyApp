package com.example.college.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.college.R
import com.example.college.databinding.FragmentSosBinding


class SosFragment : Fragment(R.layout.fragment_sos) {

    private var _binding : FragmentSosBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        _binding = FragmentSosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.policeEmergency.setOnClickListener {
            val policeIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:100")
            }
            startActivity(policeIntent)
        }

        binding.ambulanceEmergency.setOnClickListener {
            val ambulanceIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:102")
            }
            startActivity(ambulanceIntent)
        }

        binding.fireBrigadeEmergency.setOnClickListener {
            val fireBrigadeIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:101")
            }
            startActivity(fireBrigadeIntent)
        }

        binding.medicalHelplineEmergency.setOnClickListener {
            val medicalHelplineIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:104")
            }
            startActivity(medicalHelplineIntent)
        }

        binding.childHelplineEmergency.setOnClickListener {
            val childHelplineIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:1098")
            }
            startActivity(childHelplineIntent)
        }

        binding.womenHelplineEmergency.setOnClickListener {
            val womenHelplineIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:1091")
            }
            startActivity(womenHelplineIntent)
        }

        binding.disasterManagementEmergency.setOnClickListener {
            val disasterManagementIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:108")
            }
            startActivity(disasterManagementIntent)
        }

        binding.roadAccidentHelplineEmergency.setOnClickListener {
            val roadAccidentHelplineIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:1073")
            }
            startActivity(roadAccidentHelplineIntent)
        }

        binding.cyberCrimeHelplineEmergency.setOnClickListener {
            val cyberCrimeHelplineIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:155260")
            }
            startActivity(cyberCrimeHelplineIntent)
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.homeFragment, false)
        }
    }















    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = "Sos Services"
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}