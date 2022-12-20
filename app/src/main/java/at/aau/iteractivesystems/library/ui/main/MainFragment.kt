package at.aau.iteractivesystems.library.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import at.aau.iteractivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    private var tabLayoutMediator: TabLayoutMediator? = null

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(EnvironmentImpl))[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        this.binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { setupUi(it) }
    }

    override fun onDestroyView() {
        tabLayoutMediator?.detach()
        tabLayoutMediator = null
        binding = null

        super.onDestroyView()
    }

    private fun setupUi(binding: FragmentMainBinding) {
        val pageAdapter = MainPagerAdapter(this)

        binding.apply {
            pager.adapter = pageAdapter
            tabLayoutMediator = TabLayoutMediator(tabLayout, pager) { tab, position ->
                println("${MainFragment::class.simpleName} - Selected $tab at position $position")
                // TODO: Can be used to update title toolbar
            }
            tabLayoutMediator?.attach()
        }

    }
}