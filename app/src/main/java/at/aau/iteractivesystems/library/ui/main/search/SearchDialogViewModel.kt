package at.aau.iteractivesystems.library.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.repository.books.BookRepository
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.ViewState
import kotlinx.coroutines.launch

class SearchDialogViewModel(
    private val bookRepository: BookRepository,
) : ViewModel() {

    private val _state = MutableLiveData<ViewState<List<Content.SearchResult>>>(ViewState.Loading())
    val state: LiveData<ViewState<List<Content.SearchResult>>>
        get() = _state

    private var query: String? = null

    fun submitQuery(query: String?) {
        if (this.query == query) {
            // Query has not been changed, ignore it
            return
        }

        if (query.isNullOrBlank()) {
            _state.postValue(ViewState.Success(emptyList()))
            return
        }

        _state.postValue(ViewState.Loading())

        viewModelScope.launch {
            try {
                val foundBooks = bookRepository.search(query)
                val foundItems = foundBooks.map { book ->
                    Content.SearchResult(
                        id = book.id,
                        imageUrl = book.coverUrl,
                        title = book.title,
                        author = book.author,
                        publicationYear = book.publicationYear,
                    )
                }

                _state.postValue(ViewState.Success(foundItems))

            } catch (error: Throwable) {
                _state.postValue(ViewState.Failure(error))
            }
        }

        this.query = query
    }
}