package at.aau.iteractivesystems.library.persistance.books

import at.aau.iteractivesystems.library.model.Recommendation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecommendationRepositoryImpl : RecommendationRepository {

    override suspend fun getRecommendations(): List<Recommendation> = withContext(Dispatchers.IO) {
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