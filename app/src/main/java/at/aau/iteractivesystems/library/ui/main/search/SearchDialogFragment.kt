package at.aau.iteractivesystems.library.ui.main.search

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.DialogFragmentSeachBinding

private const val SEARCH_DIALOG_TAG = "SEARCH_DIALOG_TAG"

class SearchDialogFragment : DialogFragment() {

    private var binding: DialogFragmentSeachBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(
            this, ViewModelFactory(EnvironmentImpl)
        )[SearchDialogViewModel::class.java]
    }

    private val searchTextViewModel by lazy {
        ViewModelProvider(
            // note: owner is activity to allow sharing of search text
            requireActivity(), ViewModelFactory(EnvironmentImpl)
        )[SearchTextViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog_Fullscreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogFragmentSeachBinding.inflate(inflater, container, false)
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

    override fun onDismiss(dialog: DialogInterface) {
        searchTextViewModel.setQuery(null) // reset query when closing dialog, this also clears search in MainActivity
        super.onDismiss(dialog)
    }

    private fun setupUi(binding: DialogFragmentSeachBinding) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchTextViewModel.setQuery(query)
                viewModel.submitQuery(query)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchTextViewModel.query.observe(viewLifecycleOwner) { query ->
            binding.searchView.setQuery(query, false)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchDialogViewModel.State.Error -> {
                    binding.progress.isVisible = false
                }
                is SearchDialogViewModel.State.Loaded -> {
                    binding.progress.isVisible = false
                }
                SearchDialogViewModel.State.Loading -> {
                    binding.progress.isVisible = true
                }
            }
        }

        viewModel.submitQuery(searchTextViewModel.query.value)
    }

    companion object {
        fun show(fragmentManager: FragmentManager) {
            SearchDialogFragment().show(fragmentManager, SEARCH_DIALOG_TAG)
        }
    }
}