package at.aau.iteractivesystems.library.ui.main.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentExploreBinding
import at.aau.iteractivesystems.library.ui.adapter.ContentAdapter
import at.aau.iteractivesystems.library.ui.utils.ViewState

class ExploreFragment : Fragment() {

    private var binding: FragmentExploreBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(EnvironmentImpl))[ExploreViewModel::class.java]
    }

    private val adapter by lazy {
        ContentAdapter { clickedSectionItem ->
            val navController = view?.findNavController() ?: return@ContentAdapter
            val action = ExploreFragmentDirections.actionExploreToDetail(clickedSectionItem.id)

            navController.navigate(action)
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

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Failure -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.recycler.isVisible = false
                    binding.loadingView.isVisible = false
                    binding.errorView.setError(state.exception)
                }
                is ViewState.Success -> {
                    adapter.submitList(state.data)

                    binding.swipeRefresh.isRefreshing = false
                    binding.recycler.isVisible = true
                    binding.loadingView.isVisible = false
                    binding.errorView.setError(null)
                }
                is ViewState.Loading -> {
                    binding.recycler.isVisible = false
                    binding.errorView.setError(null)
                    binding.loadingView.isVisible = true
                }
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.reload()
        }
    }
}