package at.aau.iteractivesystems.library.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import at.aau.interactivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentDetailBinding
import at.aau.iteractivesystems.library.databinding.FragmentHomeBinding
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.adapter.ContentAdapter
import at.aau.iteractivesystems.library.ui.bookdetail.DetailFragmentArgs
import at.aau.iteractivesystems.library.ui.bookdetail.DetailFragmentDirections
import at.aau.iteractivesystems.library.ui.main.search.SearchFragmentDirections
import at.aau.iteractivesystems.library.ui.utils.ViewState

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val adapter by lazy {
        ContentAdapter { clickedSectionItem ->
            if (clickedSectionItem is Content.SearchResult) {
                val navController = findNavController()
                val action = HomeFragmentDirections.actionHomeToDetail(
                    //clickedSectionItem.id
                    //TODO: Why does it not take input here?
                )

                navController.navigate(action)
            }
        }
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(EnvironmentImpl)
        )[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        this.binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            setupUi(it)
        }
    }

    override fun onDestroyView() {
        binding?.recycler?.adapter = null
        binding = null
        super.onDestroyView()
    }

    private fun setupUi(binding: FragmentHomeBinding) {
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

        //You could navigate to Login if not logged in - but not necessary

        //viewModel.navigateToLogin.observe(viewLifecycleOwner) {
        //    val action = HomeFragmentDirections.actionHomeToProfile()

        //    findNavController().navigate(action)
        //}

        binding.recycler.adapter = adapter
    }
}