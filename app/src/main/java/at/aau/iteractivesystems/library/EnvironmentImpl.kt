package at.aau.iteractivesystems.library

import at.aau.iteractivesystems.library.api.ktorHttpClient
import at.aau.iteractivesystems.library.api.search.SearchApi
import at.aau.iteractivesystems.library.api.search.SearchApiImpl
import at.aau.iteractivesystems.library.repository.books.*
import at.aau.iteractivesystems.library.repository.user.UserRepository
import at.aau.iteractivesystems.library.repository.user.UserRepositoryImpl

/**
 * Singleton using us to allow concept of dependency injection.
 * In practice someone would use frameworks like Hilt or Koin being responsible
 * for providing dependencies to other classes. For the scope of this project
 * this allows us avoiding dragging in more complex topics of general software engineering.
 */
object EnvironmentImpl : Environment {

    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl()
    }

    override val booksRepository: BooksRepository by lazy {
        BooksRepositoryImpl()
    }

    override val recommendationRepository: RecommendationRepository by lazy {
        RecommendationRepositoryImpl(searchApi)
    }

    override val recentlyVisitedRepository: RecentlyVisitedRepository by lazy {
        RecentlyVisitedRepositoryImpl()
    }

    // region API

    override val searchApi: SearchApi by lazy {
        SearchApiImpl(ktorHttpClient)
    }

    // endregion
}