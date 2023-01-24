package at.aau.iteractivesystems.library.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentProfileBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable

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
        viewModel.suggestedGenre.observe(viewLifecycleOwner) { suggestedGenres ->
            val context = binding.root.context

            suggestedGenres.forEach { genre ->
                val drawable = ChipDrawable.createFromAttributes(
                    context,
                    null,
                    0,
                    com.google.android.material.R.style.Widget_Material3_Chip_Filter
                )

                val chip = Chip(context).apply {
                    text = getString(genre.subject.stringRes)

                    setChipDrawable(drawable)
                    isChecked = genre.suggest
                }

                binding.genreChipGroup.addView(chip)
            }
        }

        viewModel.isLoggedIn.observe(viewLifecycleOwner) { loggedIn ->
            if (loggedIn) {
                binding.textAccountInfo.setText(R.string.logged_in_info)
                binding.accountAction.setText(R.string.logout)
                binding.accountAction.setOnClickListener {
                    viewModel.logout()
                }
            } else {
                binding.textAccountInfo.setText(R.string.not_logged_in_info)
                binding.accountAction.setText(R.string.login)
                binding.accountAction.setOnClickListener {
                    findNavController().navigate(ProfileFragmentDirections.actionProfileToLogin())
                }
            }
        }
    }
}