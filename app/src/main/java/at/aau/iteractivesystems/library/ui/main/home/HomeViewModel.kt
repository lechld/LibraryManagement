package at.aau.iteractivesystems.library.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.repository.books.BookRepository
import at.aau.iteractivesystems.library.repository.books.BorrowedBooksRepository
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.AndroidString
import at.aau.iteractivesystems.library.ui.utils.ViewState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bookRepository: BookRepository,
    private val borrowedBooksRepository: BorrowedBooksRepository,
) : ViewModel(), BorrowedBooksRepository.Observer {

    private val _state: MutableLiveData<ViewState<List<Content>>> =
        MutableLiveData(ViewState.Loading())
    val state: LiveData<ViewState<List<Content>>>
        get() = _state

    init {
        borrowedBooksRepository.addObserver(this)
        setupContent()
    }

    override fun onCleared() {
        borrowedBooksRepository.removeObserver(this)
        super.onCleared()
    }

    override fun borrowedStateChanged() {
        setupContent()
    }

    private fun setupContent() {
        viewModelScope.launch {
            val content = mutableListOf<Content>()
            val borrowedBookIds = borrowedBooksRepository.getAll()
            val borrowedContent = borrowedBookIds.mapNotNull { bookId ->
                val book = bookRepository.getBook(bookId) ?: return@mapNotNull null

                Content.SearchResult(
                    book.id,
                    book.coverUrl,
                    book.title,
                    book.author,
                    book.publicationYear
                )
            }

            if (borrowedContent.isNotEmpty()) {
                content.add(Content.Headline(AndroidString.Resource(R.string.home_header)))
                content.addAll(borrowedContent)
            }

            _state.postValue(ViewState.Success(content))
        }
    }
}