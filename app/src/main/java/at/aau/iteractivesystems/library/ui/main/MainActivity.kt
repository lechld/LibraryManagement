package at.aau.iteractivesystems.library.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.databinding.ActivityMainBinding

/**
 * That's the main and startup activity.
 * It's view is a [FragmentContainerView] which hosts
 * all screens used by the application.
 *
 * We try follow Single-Activity-Architecture and use Navigation Component library
 * to handle transition and navigation between screens.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation(binding)
    }

    private fun setupNavigation(binding: ActivityMainBinding) {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        navController = navHost.navController

        binding.navigationView.setupWithNavController(navController)
    }
}