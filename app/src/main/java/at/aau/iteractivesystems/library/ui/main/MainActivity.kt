package at.aau.iteractivesystems.library.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import at.aau.iteractivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.ActivityMainBinding
import at.aau.iteractivesystems.library.ui.main.search.SearchDialogFragment
import at.aau.iteractivesystems.library.ui.main.search.SearchTextViewModel

/**
 * That's the main and startup activity.
 * It's view is a [FragmentContainerView] which hosts
 * all screens used by the application.
 *
 * We try follow Single-Activity-Architecture and use Navigation Component library
 * to handle transition and navigation between screens.
 */
class MainActivity : AppCompatActivity() {

    private val searchTextViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(EnvironmentImpl))[SearchTextViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation(binding)
        setupSearch(binding)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_container)

        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    private fun setupNavigation(binding: ActivityMainBinding) {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        val controller = navHost.navController

        binding.navigationView.setupWithNavController(controller)
    }

    private fun setupSearch(binding: ActivityMainBinding) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchTextViewModel.setQuery(query)
                if (!query.isNullOrEmpty()) {
                    SearchDialogFragment.show(supportFragmentManager)
                    binding.searchView.clearFocus() // Avoid auto focus on search view when dialog is closed
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchTextViewModel.query.observe(this) { query ->
            binding.searchView.setQuery(query, false)
        }
    }
}