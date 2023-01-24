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
) : ViewModel(), BorrowedBooksRepository.Observer {

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
        borrowedBooksRepository.addObserver(this)
        setupContent()
    }

    override fun onCleared() {
        borrowedBooksRepository.removeObserver(this)
        super.onCleared()
    }

    override fun borrowedStateChanged() {
        viewModelScope.launch {
            _borrowed.postValue(borrowedBooksRepository.contains(bookId))
        }
    }

    fun toggleBorrowed() {
        viewModelScope.launch {
            /*if (userRepository.getUser() == null) {
                _navigateToLogin.postValue(Unit)
                return@launch
            }*/

            if (borrowedBooksRepository.contains(bookId)) {
                borrowedBooksRepository.remove(bookId)
            } else {
                borrowedBooksRepository.add(bookId)
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
                Content.Detail(book.id, book.coverUrl),
                Content.HeadlineSmall(AndroidString.Text(book.title)),
                Content.HeadlineSmall(AndroidString.Text("")),
                Content.HeadlineSmall(AndroidString.Text("by " + book.author)),
                Content.HeadlineSmall(AndroidString.Text("")),
                Content.HeadlineSmall(AndroidString.Text("from: " + book.publicationYear)),
                Content.HeadlineSmall(AndroidString.Text("")),

                // TODO: Fix API to receive proper description
                //Content.HeadlineSmall(AndroidString.Text("Description: " + book.description))

                Content.HeadlineSmall(
                    AndroidString.Text(
                        "Description" + System.lineSeparator() + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ac cursus mi. Proin ut nisl vitae nisl consectetur interdum vitae sit amet urna. Curabitur non porta felis. Phasellus sodales consectetur erat, vitae commodo sapien. Suspendisse ante lectus, iaculis vel consequat et, vulputate eu elit. Etiam faucibus ante sed pellentesque imperdiet. Phasellus ac enim sapien. Fusce mattis feugiat rutrum. Integer finibus ligula ac aliquam lacinia. Proin ultrices libero urna, at congue justo hendrerit vel. Donec at neque nulla. Mauris efficitur dui ac augue bibendum sagittis. Nulla vulputate, metus mollis consectetur ornare, erat elit tincidunt velit, vel faucibus arcu nulla non orci. In vitae ipsum eget ipsum placerat ullamcorper. Aliquam ullamcorper viverra odio a tempor. Donec consectetur, dui venenatis sagittis maximus, ante purus scelerisque velit, et lacinia lacus nibh in nulla.\n" +
                                "\n" +
                                "Fusce convallis turpis vel felis ornare consequat ac id velit. Cras faucibus sed felis et molestie. Nulla risus metus, venenatis in leo ac, mattis porta lectus. Vivamus fermentum luctus velit, nec vulputate dui venenatis nec. Vestibulum varius sagittis urna, id mattis nulla consectetur consequat. Maecenas elit erat, semper et egestas in, tristique et dui. Pellentesque sapien velit, vulputate eu ex nec, blandit hendrerit quam. Praesent ac magna vel elit tincidunt accumsan nec et diam. Mauris consectetur pretium ante, nec interdum mauris mattis sit amet. Fusce malesuada ante nisl, in iaculis leo facilisis varius. Morbi purus turpis, facilisis nec arcu vitae, dignissim varius enim. Cras vestibulum quam nulla, lacinia condimentum neque vestibulum in. Mauris rhoncus interdum nisi sed sollicitudin. Curabitur imperdiet bibendum imperdiet. Proin vestibulum lectus in mi tincidunt, at venenatis risus tincidunt."
                    )
                )
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