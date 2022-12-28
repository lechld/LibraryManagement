package at.aau.iteractivesystems.library.repository.books

import at.aau.iteractivesystems.library.model.Recommendation

interface RecommendationRepository {
    suspend fun getRecommendations(): List<Recommendation>
}