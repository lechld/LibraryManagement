package at.aau.iteractivesystems.library.ui.main.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import at.aau.iteractivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentDiscoverBinding
import at.aau.iteractivesystems.library.ui.main.adapter.ContentAdapter

class DiscoverFragment : Fragment() {

    private var binding: FragmentDiscoverBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(EnvironmentImpl))[DiscoverViewModel::class.java]
    }

    private val adapter by lazy {
        ContentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDiscoverBinding.inflate(inflater, container, false)
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

    private fun setupUi(binding: FragmentDiscoverBinding) {
        binding.recycler.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DiscoverViewModel.State.Loaded -> {
                    adapter.submitList(state.items)
                }
                is DiscoverViewModel.State.Error -> {
                    // TODO
                }
                DiscoverViewModel.State.Loading -> {
                    // TODO
                }
            }
        }
    }
}