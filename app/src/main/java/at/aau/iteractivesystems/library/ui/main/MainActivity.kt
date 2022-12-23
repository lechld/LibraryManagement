package at.aau.iteractivesystems.library.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        val controller = navHost.navController

        binding.navigationView.setupWithNavController(controller)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_container)

        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}