package at.aau.iteractivesystems.library.ui.main.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.persistance.books.BooksRepository
import at.aau.iteractivesystems.library.persistance.books.RecentlyVisitedRepository
import at.aau.iteractivesystems.library.persistance.books.RecommendationRepository
import at.aau.iteractivesystems.library.ui.main.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.AndroidString
import kotlinx.coroutines.launch

class DiscoverViewModel(
    private val recommendationRepository: RecommendationRepository,
    private val booksRepository: BooksRepository,
    private val recentlyVisitedRepository: RecentlyVisitedRepository,
) : ViewModel() {

    sealed class State {
        object Loading : State()
        data class Error(val error: Exception) : State()
        data class Loaded(val items: List<Content>) : State()
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
            val recentlyVisited = recentlyVisitedRepository.getLatestBookIds()
            val content = mutableListOf<Content>()

            content.add(Content.HeadlineSmall(AndroidString.Resource(R.string.recently_visited)))

            val recentItems = recentlyVisited.mapNotNull { recent ->
                val book = booksRepository.getBook(recent) ?: return@mapNotNull null

                Content.Section.Item(book.isbn, book.image, book.title)
            }

            content.add(Content.Section.Small("recently", recentItems))

            content.add(Content.Headline(AndroidString.Resource(R.string.discover_content_header)))

            recommendations.forEach { recommendation ->
                val items = recommendation.bookIds.mapNotNull { bookId ->
                    val book = booksRepository.getBook(bookId) ?: return@mapNotNull null

                    Content.Section.Item(book.isbn, book.image, book.title)
                }

                if (items.isNotEmpty()) {
                    content.add(Content.HeadlineSmall(AndroidString.Text(recommendation.title)))
                    content.add(Content.Section.Big(recommendation.id, items))
                }
            }

            _state.postValue(State.Loaded(content))
        }
    }
}