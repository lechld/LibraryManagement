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
            Recommendation("1", "R - Books we love", listOf("1", "2", "3", "4", "5", "6")),
            Recommendation("2", "R - Recently returned", listOf("1", "2", "3", "4", "5", "6")),
            Recommendation("3", "R - Romance", listOf("1", "2", "3", "4", "5", "6")),
            Recommendation("4", "R - Kids", listOf("1", "2", "3", "4", "5", "6")),
            Recommendation("5", "R - Thriller", listOf("1", "2", "3")),
            Recommendation("6", "R - Textbooks", listOf("1", "2", "3", "4", "5", "6"))
        )
    }
}