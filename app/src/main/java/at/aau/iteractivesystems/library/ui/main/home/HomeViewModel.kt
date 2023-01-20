package at.aau.iteractivesystems.library.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.repository.books.BookRepository
import at.aau.iteractivesystems.library.repository.books.BorrowedBooksRepository
import at.aau.iteractivesystems.library.repository.user.UserRepository
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.SingleLiveEvent
import at.aau.iteractivesystems.library.ui.utils.ViewState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bookRepository: BookRepository,
    private val borrowedBooksRepository: BorrowedBooksRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state: MutableLiveData<ViewState<List<Content>>> =
        MutableLiveData(ViewState.Loading())
    val state: LiveData<ViewState<List<Content>>>
        get() = _state

    private val _navigateToLogin = SingleLiveEvent<Unit>()
    val navigateToLogin: LiveData<Unit>
        get() = _navigateToLogin

    init {
        setupContent()
    }

    private fun setupContent() {
        viewModelScope.launch {
            if (userRepository.getUser() == null) {
                _navigateToLogin.postValue(Unit)
            }

            val content = mutableListOf<Content>()
            val borrowedBookIds = borrowedBooksRepository.getAll()

            val contentItems = borrowedBookIds.mapNotNull { bookId ->
                val book = bookRepository.getBook(bookId) ?: return@mapNotNull null

                Content.SearchResult(
                    book.id,
                    book.coverUrl,
                    book.title,
                    book.author,
                    book.publicationYear
                )
            }

            content.addAll(contentItems)
            content.add(
                Content.Home(placeholder = null)
            )
            _state.postValue(ViewState.Success(content))
        }
    }
}