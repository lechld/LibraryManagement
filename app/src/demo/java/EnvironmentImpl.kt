package at.aau.interactivesystems.library

import at.aau.interactivesystems.library.api.SearchApiImpl
import at.aau.iteractivesystems.library.Environment
import at.aau.iteractivesystems.library.repository.books.RecommendationRepository
import at.aau.iteractivesystems.library.repository.books.RecommendationsRepositoryImpl
import at.aau.iteractivesystems.library.repository.user.UserRepository
import at.aau.iteractivesystems.library.repository.user.UserRepositoryImpl

object EnvironmentImpl : Environment {
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl()
    }

    override val recommendationRepository: RecommendationRepository by lazy {
        RecommendationsRepositoryImpl(SearchApiImpl())
    }
}