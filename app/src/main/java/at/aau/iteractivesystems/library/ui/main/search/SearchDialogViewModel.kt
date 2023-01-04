package at.aau.iteractivesystems.library.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.api.search.SearchApi
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.ViewState
import kotlinx.coroutines.launch

class SearchDialogViewModel(
    private val searchApi: SearchApi,
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
                val searchResult = searchApi.searchByTitle(query)
                val searchItems = searchResult.docs.mapNotNull { document ->
                    val id = document.coverId ?: return@mapNotNull null
                    val author = document.authorName.firstOrNull() ?: return@mapNotNull null

                    Content.SearchResult(
                        id = id,
                        imageUrl = document.coverUrl,
                        title = document.title,
                        author = author,
                        publicationYear = document.firstPublishYear
                    )
                }

                _state.postValue(ViewState.Success(searchItems))

            } catch (error: Throwable) {
                _state.postValue(ViewState.Failure(error))
            }
        }

        this.query = query
    }
}