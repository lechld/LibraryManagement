package at.aau.iteractivesystems.library.ui.main.profile.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

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
        binding.textviewRegister.setOnClickListener {
            val navController = findNavController()
            val navAction = LoginFragmentDirections.actionLoginToRegister()

            navController.navigate(navAction)
        }

        binding.loginButton.setOnClickListener {
            val mail = binding.email.text.toString()
            val password = binding.email.text.toString()

            lifecycleScope.launch {
                viewModel.login(mail, password).onSuccess {
                    findNavController().popBackStack()
                }.onFailure { _ ->
                    Snackbar.make(it, R.string.invalid_login_data, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}