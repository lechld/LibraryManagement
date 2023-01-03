package at.aau.interactivesystems.library.repository.books

import at.aau.iteractivesystems.library.MOCK_LOADING_DELAY
import at.aau.iteractivesystems.library.model.Recommendation
import at.aau.iteractivesystems.library.repository.books.RecommendationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RecommendationRepositoryImpl : RecommendationRepository {

    override suspend fun getRecommendations(): List<Recommendation> = withContext(Dispatchers.IO) {
        delay(MOCK_LOADING_DELAY) // fake api call

        listOf(
            Recommendation("1", "Books we love", listOf("1", "2", "3", "4", "5", "6")),
            Recommendation("2", "Recently returned", listOf("1", "2", "3", "4", "5", "6")),
            Recommendation("3", "Romance", listOf("1", "2", "3", "4", "5", "6")),
            Recommendation("4", "Kids", listOf("1", "2", "3", "4", "5", "6")),
            Recommendation("5", "Thriller", listOf("1", "2", "3")),
            Recommendation("6", "Textbooks", listOf("1", "2", "3", "4", "5", "6"))
        )
    }
}