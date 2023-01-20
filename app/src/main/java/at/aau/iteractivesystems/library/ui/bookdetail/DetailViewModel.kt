package at.aau.iteractivesystems.library.ui.bookdetail

import androidx.lifecycle.*
import at.aau.iteractivesystems.library.Environment
import at.aau.iteractivesystems.library.repository.books.BookRepository
import at.aau.iteractivesystems.library.repository.books.BorrowedBooksRepository
import at.aau.iteractivesystems.library.repository.user.UserRepository
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.AndroidString
import at.aau.iteractivesystems.library.ui.utils.SingleLiveEvent
import at.aau.iteractivesystems.library.ui.utils.ViewState
import kotlinx.coroutines.launch

class DetailViewModel(
    private val bookId: String,
    private val bookRepository: BookRepository,
    private val borrowedBooksRepository: BorrowedBooksRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state: MutableLiveData<ViewState<List<Content>>> =
        MutableLiveData(ViewState.Loading())
    val state: LiveData<ViewState<List<Content>>>
        get() = _state

    private val _borrowed = MutableLiveData(borrowedBooksRepository.contains(bookId))
    val borrowed: LiveData<Boolean>
        get() = _borrowed

    private val _navigateToLogin = SingleLiveEvent<Unit>()
    val navigateToLogin: LiveData<Unit>
        get() = _navigateToLogin

    init {
        setupContent()
    }

    fun toggleBorrowed() {
        viewModelScope.launch {
            /*if (userRepository.getUser() == null) {
                _navigateToLogin.postValue(Unit)
                return@launch
            }*/

            if (borrowedBooksRepository.contains(bookId)) {
                borrowedBooksRepository.remove(bookId)
                _borrowed.postValue(false)
            } else {
                borrowedBooksRepository.add(bookId)
                _borrowed.postValue(true)
            }
        }
    }

    private fun setupContent() {
        viewModelScope.launch {
            val book = bookRepository.getBook(bookId)

            if (book == null) {
                _state.postValue(ViewState.Failure(IllegalStateException("Book not found!")))
                return@launch
            }

            val items = listOf(
                Content.Detail(book.id, book.coverUrl,book.title, "by "+book.author, "published "+book.publicationYear),
                Content.HeadlineSmall(AndroidString.Text("Description: "+book.description))
            )

            _state.postValue(ViewState.Success(items))

        }
    }

    @Suppress("UNCHECKED_CAST") // Can safely ignore that warning, we check isAssignableFrom
    class Factory(
        private val bookId: String,
        private val environment: Environment,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                DetailViewModel(
                    bookId = bookId,
                    bookRepository = environment.bookRepository,
                    borrowedBooksRepository = environment.borrowedBooksRepository,
                    userRepository = environment.userRepository,
                ) as T
            } else throw IllegalArgumentException("Unknown ViewModel class.")
        }
    }
}