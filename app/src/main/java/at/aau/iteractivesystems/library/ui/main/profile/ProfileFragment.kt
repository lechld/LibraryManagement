package at.aau.iteractivesystems.library.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(EnvironmentImpl))[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
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

    private fun setupUi(binding: FragmentProfileBinding) {
        viewModel.isLoggedIn.observe(viewLifecycleOwner) { loggedIn ->
            binding.loginButton.isVisible = !loggedIn
            binding.logoutButton.isVisible = loggedIn
        }

        binding.loginButton.setOnClickListener {
            val navAction = ProfileFragmentDirections.actionProfileToLogin()

            findNavController().navigate(navAction)
        }

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            val navAction = ProfileFragmentDirections.actionProfileToLogin()

            findNavController().navigate(navAction)
        }
    }
}