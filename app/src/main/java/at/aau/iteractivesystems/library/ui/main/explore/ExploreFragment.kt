package at.aau.iteractivesystems.library.ui.main.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentExploreBinding
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.adapter.ContentAdapter
import at.aau.iteractivesystems.library.ui.utils.ViewState

class ExploreFragment : Fragment() {

    private var binding: FragmentExploreBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(EnvironmentImpl))[ExploreViewModel::class.java]
    }

    private val adapter by lazy {
        ContentAdapter { clickedSectionItem ->
            if (clickedSectionItem is Content.Section.Item) {
                val navController = findNavController()
                val action = ExploreFragmentDirections.actionExploreToDetail(clickedSectionItem.id)

                navController.navigate(action)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentExploreBinding.inflate(inflater, container, false)
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

    private fun setupUi(binding: FragmentExploreBinding) {
        binding.recycler.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state: ViewState<List<Content>> ->
            when (state) {
                is ViewState.Failure<List<Content>> -> {
                    binding.recycler.isVisible = false
                    binding.loadingView.hide()
                    binding.errorView.show(state.exception)
                }
                is ViewState.Success<List<Content>> -> {
                    adapter.submitList(state.data)

                    binding.recycler.isVisible = true
                    binding.loadingView.hide()
                    binding.errorView.hide()
                }
                is ViewState.Loading<List<Content>> -> {
                    binding.recycler.isVisible = false
                    binding.errorView.hide()
                    binding.loadingView.show()
                }
            }
        }

        binding.errorView.reloadListener = {
            viewModel.reload()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    val navAction = ExploreFragmentDirections.actionExploreToSearch(query)
                    findNavController().navigate(navAction)

                    binding.searchView.setQuery("", false) // Reset query after navigation.
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }
}