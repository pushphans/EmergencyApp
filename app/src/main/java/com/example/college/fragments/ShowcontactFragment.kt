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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.college.R
import com.example.college.adapter.ContactAdapter
import com.example.college.databinding.FragmentShowcontactBinding
import com.example.college.db.ContactViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ShowcontactFragment : Fragment(R.layout.fragment_showcontact) {

    private var _binding : FragmentShowcontactBinding? = null
    private val binding get() = _binding!!

    private lateinit var contactAdapter : ContactAdapter
    private val viewModel : ContactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowcontactBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        lifecycleScope.launch {
            viewModel.getAllContacts().collectLatest {
                contactAdapter.submitList(it)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.homeFragment, false)
        }


    }



    private fun setUpRecyclerView(){
        contactAdapter = ContactAdapter()
        contactAdapter.setonOnClicked {clickedContact ->
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${clickedContact.number}")
            }
            startActivity(intent)

        }
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = contactAdapter
    }










    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = "Show Emergency Contacts"
    }



    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}