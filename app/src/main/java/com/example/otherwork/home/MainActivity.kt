package com.example.otherwork.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.otherwork.R
import com.example.otherwork.common.SharedPrefs
import com.example.otherwork.databinding.ActivityMainBinding
import com.example.otherwork.extention.setStatusBarColor

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    lateinit var sharedPrefs: SharedPrefs

    var isAdmin = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(R.color.white, R.color.black)

        sharedPrefs = SharedPrefs(this)
        initialize()
        initiateBottomNavigation()

        if (isAdmin) {
            binding.bottomNavigationView.menu.findItem(R.id.nav_invoices).setVisible(false)
            binding.bottomNavigationView.menu.findItem(R.id.itemFirst).setVisible(true)
            binding.bottomNavigationView.menu.findItem(R.id.itemLast).setVisible(true)
        }else{
            binding.bottomNavigationView.menu.findItem(R.id.nav_invoices).setVisible(true)
            binding.bottomNavigationView.menu.findItem(R.id.itemFirst).setVisible(false)
            binding.bottomNavigationView.menu.findItem(R.id.itemLast).setVisible(false)
        }

    }

    private fun initialize() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragmentHome->{
                    binding.bottomNavigationView.isVisible = true
                }
                R.id.fragmentInvoices->{
                    binding.bottomNavigationView.isVisible = true
                }
                R.id.fragmentMore->{
                    binding.bottomNavigationView.isVisible = true
                }
                else->{
                    binding.bottomNavigationView.isVisible = false
                }
            }
        }

    }

    private fun initiateBottomNavigation() {
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}