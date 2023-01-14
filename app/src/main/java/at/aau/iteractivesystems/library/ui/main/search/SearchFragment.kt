package at.aau.iteractivesystems.library.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentSearchBinding
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.adapter.ContentAdapter
import at.aau.iteractivesystems.library.ui.utils.ViewState

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null

    private val navArgs by navArgs<SearchFragmentArgs>()

    private val query by lazy {
        navArgs.query
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this, ViewModelFactory(EnvironmentImpl)
        )[SearchViewModel::class.java]
    }

    private val adapter by lazy {
        ContentAdapter { clickedSectionItem ->
            if (clickedSectionItem is Content.SearchResult) {
                val navController = findNavController()
                val action = SearchFragmentDirections.actionSearchToDetail(clickedSectionItem.id)

                navController.navigate(action)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        this.binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { setupUi(it) }
    }

    override fun onDestroyView() {
        binding?.recycler?.adapter = null
        binding = null
        super.onDestroyView()
    }

    private fun setupUi(binding: FragmentSearchBinding) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.submitQuery(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Failure<List<Content.SearchResult>> -> {
                    binding.progress.isVisible = false
                }
                is ViewState.Success<List<Content.SearchResult>> -> {
                    adapter.submitList(state.data)
                    binding.progress.isVisible = false
                }
                is ViewState.Loading<List<Content.SearchResult>> -> {
                    binding.progress.isVisible = true
                }
            }
        }

        binding.searchView.setQuery(query, true)
        binding.recycler.adapter = adapter
    }
}