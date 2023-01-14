package at.aau.iteractivesystems.library.ui.bookdetail

import androidx.lifecycle.*
import at.aau.iteractivesystems.library.Environment
import at.aau.iteractivesystems.library.repository.books.BookRepository
import at.aau.iteractivesystems.library.repository.books.BorrowedBooksRepository
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.AndroidString
import at.aau.iteractivesystems.library.ui.utils.ViewState
import kotlinx.coroutines.launch

class DetailViewModel(
    private val bookId: String,
    private val bookRepository: BookRepository,
    private val borrowedBooksRepository: BorrowedBooksRepository,
) : ViewModel() {

    private val _state: MutableLiveData<ViewState<List<Content>>> =
        MutableLiveData(ViewState.Loading())
    val state: LiveData<ViewState<List<Content>>>
        get() = _state

    private val _borrowed = MutableLiveData(borrowedBooksRepository.contains(bookId))
    val borrowed: LiveData<Boolean>
        get() = _borrowed

    init {
        setupContent()
    }

    fun toggleBorrowed() {
        if (borrowedBooksRepository.contains(bookId)) {
            borrowedBooksRepository.remove(bookId)
            _borrowed.postValue(false)
        } else {
            borrowedBooksRepository.add(bookId)
            _borrowed.postValue(true)
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
                Content.Headline(AndroidString.Text(book.title)),
                Content.Headline(AndroidString.Text("By ${book.author}. Published ${book.publicationYear}.")),
                Content.HeadlineSmall(
                    AndroidString.Text(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a ante mattis, consequat urna et, scelerisque metus. Nulla pretium cursus metus vel commodo. In commodo, augue in ultricies tempus, tellus nibh blandit justo, vel faucibus lectus velit nec eros. Sed vel lectus eget urna feugiat bibendum. Nulla imperdiet mauris id libero consequat facilisis. Vivamus eu accumsan leo. Mauris erat urna, feugiat eget enim a, mattis gravida ligula.\n" +
                                "\n" +
                                "Nam ullamcorper quam leo, vel elementum lacus ullamcorper eget. Curabitur auctor magna non lorem egestas eleifend. Suspendisse egestas sed metus et auctor. Donec scelerisque magna a nisl fringilla tempor. Duis consequat, tortor eget ornare euismod, sem est auctor nisi, a pharetra ligula sapien vitae elit. Curabitur ut pellentesque mi. Curabitur ut pretium sapien, a gravida diam. Proin laoreet libero nec eros porttitor, non volutpat felis condimentum. Pellentesque convallis ligula vel dolor gravida faucibus.\n" +
                                "\n" +
                                "Suspendisse potenti. Sed facilisis orci eget purus finibus rutrum. In sodales commodo elit, et tincidunt nisl. Nam scelerisque fermentum molestie. Maecenas sit amet felis nulla. Sed aliquam nisi ac mauris accumsan, eu auctor nunc congue. Quisque id iaculis ex. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aliquam vestibulum ex et leo bibendum feugiat. Nulla sed porta ligula, in bibendum orci. Nullam id libero id sem euismod posuere eget ac lacus. Quisque interdum erat finibus viverra malesuada. Etiam iaculis, nulla in eleifend hendrerit, orci enim posuere tortor, in viverra ipsum dolor in libero. Nulla consectetur lectus sed rhoncus porta.\n" +
                                "\n" +
                                "Etiam nec ipsum urna. Nunc feugiat, arcu id venenatis porta, metus odio euismod tortor, sit amet consectetur mi nisl quis dui. Ut quis risus fermentum, pretium diam non, accumsan est. Nulla et sapien dapibus, vulputate leo at, pellentesque ipsum. Aenean in eleifend est. Sed tortor ipsum, commodo id commodo eu, cursus quis orci. Praesent vulputate vulputate convallis. Nam vestibulum risus at condimentum malesuada. Vestibulum a tortor sapien. Duis quis lacus eu lectus aliquam sagittis. Phasellus et pharetra erat, sit amet auctor mauris. Curabitur ullamcorper rhoncus tincidunt. Cras et lorem vehicula, laoreet nisl ut, ullamcorper orci. Vivamus quis metus id purus laoreet rhoncus finibus non tortor. Vivamus convallis euismod diam ut molestie."
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
                    borrowedBooksRepository = environment.borrowedBooksRepository
                ) as T
            } else throw IllegalArgumentException("Unknown ViewModel class.")
        }
    }
}