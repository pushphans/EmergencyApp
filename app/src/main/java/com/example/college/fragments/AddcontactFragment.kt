package com.example.college.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.college.R
import com.example.college.databinding.FragmentAddcontactBinding
import com.example.college.db.ContactViewModel
import com.example.college.db.DataClass
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class AddcontactFragment : Fragment(R.layout.fragment_addcontact) {

    private var _binding: FragmentAddcontactBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactViewModel by activityViewModels()
    private lateinit var existingContacts: List<DataClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddcontactBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.getAllContacts().collectLatest {
                existingContacts = it
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.homeFragment, false)
        }



        binding.btnSave.setOnClickListener {
            val nameC = binding.etName.text.toString().trim()
            val numberC = binding.etNumber.text.toString().trim()
            if (nameC.isNotEmpty() && numberC.isNotEmpty()) {

                val contactExists = existingContacts.any {
                    it.name == nameC || it.number == numberC
                }
                if(contactExists == true){
                    Toast.makeText(requireContext(), "Contact already exist", Toast.LENGTH_SHORT).show()

                }else{
                    val dataClass = DataClass(name = nameC,number = numberC)
                    viewModel.insertContact(dataClass)
                    Toast.makeText(requireContext(),"Fast dial contact stored successfully! You can fast dial this contact from Fast Dail page", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack(R.id.homeFragment, false)
                }

            }else{
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = "Add Emergency Contacts"
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}