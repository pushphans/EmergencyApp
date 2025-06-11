package com.example.college

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.college.databinding.ActivityMainBinding
import com.example.college.db.ContactDatabase
import com.example.college.db.ContactRepository
import com.example.college.db.ContactViewModel
import com.example.college.db.VMFactory
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class
MainActivity : AppCompatActivity(){

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel : ContactViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = ContactDatabase.getDatabase(this)
        val repository = ContactRepository(db)
        viewModel = ViewModelProvider(this, VMFactory(repository))[ContactViewModel::class.java]


       setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)
        supportActionBar?.title = "Home"


        binding.drawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.emergencyContacts -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.addcontactFragment)
                }
                R.id.about ->{
                    findNavController(R.id.fragmentContainerView).navigate(R.id.aboutFragment)
                }
                R.id.privacy ->{
                    findNavController(R.id.fragmentContainerView).navigate(R.id.privacyFragment)
                }
            }
            binding.drawerLayout.close()
            true

        }


    }


}