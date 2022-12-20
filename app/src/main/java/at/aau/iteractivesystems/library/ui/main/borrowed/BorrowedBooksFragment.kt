package at.aau.iteractivesystems.library.ui.main.borrowed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import at.aau.iteractivesystems.library.EnvironmentImpl
import at.aau.iteractivesystems.library.ViewModelFactory
import at.aau.iteractivesystems.library.databinding.FragmentBorrowedBooksBinding

class BorrowedBooksFragment : Fragment() {

    private var binding: FragmentBorrowedBooksBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(EnvironmentImpl)
        )[BorrowedBooksViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBorrowedBooksBinding.inflate(inflater, container, false)
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

    private fun setupUi(binding: FragmentBorrowedBooksBinding) {
        // TODO
    }
}