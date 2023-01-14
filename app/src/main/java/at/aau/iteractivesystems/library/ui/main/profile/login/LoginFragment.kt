package at.aau.iteractivesystems.library.ui.main.profile.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(EnvironmentImpl))[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
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

    private fun setupUi(binding: FragmentLoginBinding) {
        binding.root.setOnClickListener {
            val navController = findNavController()
            val navAction = LoginFragmentDirections.actionLoginToRegister()

            navController.navigate(navAction)
        }
    }
}