package at.aau.iteractivesystems.library.ui.main.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.persistance.books.BooksRepository
import at.aau.iteractivesystems.library.persistance.books.RecommendationRepository
import at.aau.iteractivesystems.library.ui.main.discover.adapter.DiscoverElement
import kotlinx.coroutines.launch

class DiscoverViewModel(
    private val recommendationRepository: RecommendationRepository,
    private val booksRepository: BooksRepository,
) : ViewModel() {

    sealed class State {
        object Loading : State()
        data class Error(val error: Exception) : State()
        data class Content(val items: List<DiscoverElement>) : State()
    }

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State>
        get() = _state

    init {
        reload()
    }

    // TODO: Could be public if we allow some pull-to-refresh or something similar
    private fun reload() {
        viewModelScope.launch {
            val recommendations = recommendationRepository.getRecommendations()

            val title = DiscoverElement.Text(R.string.discover_content_header)

            val sections = recommendations.map { recommendation ->
                val sectionItems = recommendation.bookIds.mapNotNull { bookId ->
                    val book = booksRepository.getBook(bookId) ?: return@mapNotNull null

                    DiscoverElement.Section.Item(bookId, book.image, book.title)
                }

                DiscoverElement.Section(recommendation.id, recommendation.title, sectionItems)
            }

            val discoverContent = mutableListOf<DiscoverElement>(title)

            discoverContent.addAll(sections)

            _state.postValue(State.Content(discoverContent))
        }
    }
}