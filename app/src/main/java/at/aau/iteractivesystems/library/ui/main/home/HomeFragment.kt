package at.aau.iteractivesystems.library.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(EnvironmentImpl)
        )[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        this.binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { setupUi(it) }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupUi(binding: FragmentHomeBinding) {
        // TODO
    }
}