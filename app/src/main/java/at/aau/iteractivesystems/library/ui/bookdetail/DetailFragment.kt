package at.aau.iteractivesystems.library.ui.bookdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentDetailBinding
import at.aau.iteractivesystems.library.ui.adapter.ContentAdapter
import at.aau.iteractivesystems.library.ui.main.FloatingActionViewModel
import at.aau.iteractivesystems.library.ui.utils.ViewState

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null

    private val navArgs by navArgs<DetailFragmentArgs>()

    private val bookId by lazy {
        navArgs.bookId
    }

    private val adapter by lazy {
        ContentAdapter {
            // TODO: Need to change the way we handle recycler clicks
        }
    }

    private val floatingActionViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelFactory(EnvironmentImpl)
        )[FloatingActionViewModel::class.java]
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
        binding?.let { setupUi(it) }
    }

    override fun onDestroyView() {
        floatingActionViewModel.setAction(FloatingActionViewModel.Action.Hidden)
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

        binding.recycler.adapter = adapter

        floatingActionViewModel.selected.observe(viewLifecycleOwner) {
            viewModel.toggleBorrowed()
        }

        viewModel.action.observe(viewLifecycleOwner) { action ->
            floatingActionViewModel.setAction(action)
        }
    }
}