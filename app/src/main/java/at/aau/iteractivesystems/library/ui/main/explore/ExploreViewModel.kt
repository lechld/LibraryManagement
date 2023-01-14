package at.aau.iteractivesystems.library.ui.main.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.repository.books.BookRepository
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.AndroidString
import at.aau.iteractivesystems.library.ui.utils.ViewState
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val bookRepository: BookRepository,
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

                content.addAll(getSuggestedSection())

                _state.postValue(ViewState.Success(content))
            } catch (error: Exception) {
                _state.postValue(ViewState.Failure(error))
            }
        }
    }

    private suspend fun getSuggestedSection(): List<Content> {
        val recommendations = bookRepository.getRecommendations()
        val content = mutableListOf<Content>()

        content.add(Content.Headline(AndroidString.Resource(R.string.discover_content_header)))

        recommendations.forEach { recommendation ->
            val items = recommendation.books.map { recommendationItem ->
                Content.Section.Item(
                    id = recommendationItem.id,
                    imageUrl = recommendationItem.coverUrl,
                    title = recommendationItem.title
                )
            }

            if (items.isNotEmpty()) {
                content.add(Content.HeadlineSmall(AndroidString.Resource(recommendation.titleRes)))
                content.add(Content.Section.Big(recommendation.id, items))
            }
        }

        return content
    }
}