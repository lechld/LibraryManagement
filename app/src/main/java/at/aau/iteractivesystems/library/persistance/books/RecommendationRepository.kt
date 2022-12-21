package at.aau.iteractivesystems.library.persistance.books

import at.aau.iteractivesystems.library.model.Recommendation

interface RecommendationRepository {
    suspend fun getRecommendations(): List<Recommendation>
}