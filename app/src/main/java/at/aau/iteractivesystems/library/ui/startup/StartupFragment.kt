package at.aau.iteractivesystems.library.ui.startup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import at.aau.iteractivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentStartupBinding

class StartupFragment : Fragment() {

    private var binding: FragmentStartupBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(EnvironmentImpl))[StartupViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartupBinding.inflate(inflater, container, false)
        this.binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { setupUi(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupUi(binding: FragmentStartupBinding) {
        val navController = findNavController()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                StartupViewModel.State.Loading -> {
                }
                StartupViewModel.State.LoggedIn -> {
                    navController.navigate(StartupFragmentDirections.startupToMain())
                }
                StartupViewModel.State.NotLoggedIn -> {
                    navController.navigate(StartupFragmentDirections.startupToLogin())
                }
            }
        }
    }
}