package at.aau.iteractivesystems.library.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.aau.iteractivesystems.library.Environment

class DetailViewModel(
    private val bookId: String,
) : ViewModel() {

    @Suppress("UNCHECKED_CAST") // Can safely ignore that warning, we check isAssignableFrom
    class Factory(
        private val bookId: String,
        private val environment: Environment,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                DetailViewModel(bookId) as T
            } else throw IllegalArgumentException("Unknown ViewModel class.")
        }
    }
}