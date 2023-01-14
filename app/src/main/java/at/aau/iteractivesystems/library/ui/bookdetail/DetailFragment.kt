package at.aau.iteractivesystems.library.ui.bookdetail

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.databinding.FragmentDetailBinding
import at.aau.iteractivesystems.library.ui.adapter.ContentAdapter
import at.aau.iteractivesystems.library.ui.utils.ViewState
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null

    private val navArgs by navArgs<DetailFragmentArgs>()

    private val bookId by lazy {
        navArgs.bookId
    }

    private val adapter by lazy {
        ContentAdapter()
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            DetailViewModel.Factory(bookId, EnvironmentImpl)
        )[DetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        this.binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            setupUi(it)
            setupFab(it)
        }
    }

    override fun onDestroyView() {
        binding?.recycler?.adapter = null
        binding = null
        super.onDestroyView()
    }

    private fun setupUi(binding: FragmentDetailBinding) {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Failure -> {
                    // TODO
                }
                is ViewState.Loading -> {
                    // TODO
                }
                is ViewState.Success -> {
                    adapter.submitList(state.data)
                }
            }
        }

        viewModel.navigateToLogin.observe(viewLifecycleOwner) {
            val action = DetailFragmentDirections.actionDetailToProfile()

            findNavController().navigate(action)
        }

        binding.recycler.adapter = adapter
    }

    private fun setupFab(binding: FragmentDetailBinding) {
        val context = binding.root.context
        val addIcon = ResourcesCompat.getDrawable(resources, R.drawable.add, context.theme)
        val removeIcon = ResourcesCompat.getDrawable(resources, R.drawable.remove, context.theme)
        val addColor = MaterialColors.getColor(
            binding.root, com.google.android.material.R.attr.colorPrimaryInverse
        )
        val removeColor = MaterialColors.getColor(
            binding.root, com.google.android.material.R.attr.colorTertiary
        )

        viewModel.borrowed.observe(viewLifecycleOwner) { borrowed ->
            if (borrowed) {
                binding.fab.backgroundTintList = ColorStateList.valueOf(removeColor)
                binding.fab.setImageDrawable(removeIcon)
            } else {
                binding.fab.backgroundTintList = ColorStateList.valueOf(addColor)
                binding.fab.setImageDrawable(addIcon)
            }
        }

        binding.fab.setOnClickListener {
            showToggleConfirmation(context)
        }
    }

    private fun showToggleConfirmation(context: Context) {
        val isBorrowed = viewModel.borrowed.value ?: return

        val title = if (isBorrowed) {
            R.string.dialog_return_title
        } else R.string.dialog_borrow_title

        val message = if (isBorrowed) {
            R.string.dialog_return_message
        } else R.string.dialog_borrow_message

        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.dialog_yes) { _, _ ->
                viewModel.toggleBorrowed()
            }
            .setNegativeButton(R.string.dialog_no) { _, _ ->
                // do nothing
            }
            .show()
    }
}