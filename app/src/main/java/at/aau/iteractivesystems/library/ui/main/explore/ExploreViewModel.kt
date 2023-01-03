package at.aau.iteractivesystems.library.ui.main.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.repository.books.BooksRepository
import at.aau.iteractivesystems.library.repository.books.RecentlyVisitedRepository
import at.aau.iteractivesystems.library.repository.books.RecommendationRepository
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.AndroidString
import at.aau.iteractivesystems.library.ui.utils.ViewState
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val recommendationRepository: RecommendationRepository,
    private val booksRepository: BooksRepository,
    private val recentlyVisitedRepository: RecentlyVisitedRepository,
) : ViewModel() {

    private val _state: MutableLiveData<ViewState<List<Content>>> =
        MutableLiveData(ViewState.Loading())

    val state: LiveData<ViewState<List<Content>>>
        get() = _state

    init {
        reload()
    }

    fun reload() {
        _state.postValue(ViewState.Loading(null))

        viewModelScope.launch {
            try {
                val content = mutableListOf<Content>()

                content.addAll(getRecentlyVisitedSection())
                content.addAll(getSuggestedSection())

                _state.postValue(ViewState.Success(content))
            } catch (error: Exception) {
                _state.postValue(ViewState.Failure(error))
            }
        }
    }

    private suspend fun getRecentlyVisitedSection(): List<Content> {
        val recentlyVisited = recentlyVisitedRepository.getLatestBookIds()
        val content = mutableListOf<Content>()

        content.add(Content.HeadlineSmall(AndroidString.Resource(R.string.recently_visited)))

        val recentItems = recentlyVisited.mapNotNull { recent ->
            val book = booksRepository.getBook(recent) ?: return@mapNotNull null

            Content.Section.Item(book.isbn, book.image, book.title)
        }

        content.add(Content.Section.Small("recently", recentItems))

        return content
    }

    private suspend fun getSuggestedSection(): List<Content> {
        val recommendations = recommendationRepository.getRecommendations()
        val content = mutableListOf<Content>()

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

        return content
    }
}