package at.aau.iteractivesystems.library

import at.aau.iteractivesystems.library.api.search.SearchApi
import at.aau.iteractivesystems.library.repository.books.BooksRepository
import at.aau.iteractivesystems.library.repository.books.RecentlyVisitedRepository
import at.aau.iteractivesystems.library.repository.books.RecommendationRepository
import at.aau.iteractivesystems.library.repository.user.UserRepository

/**
 * @see EnvironmentImpl
 */
interface Environment {
    val userRepository: UserRepository
    val booksRepository: BooksRepository
    val recommendationRepository: RecommendationRepository
    val recentlyVisitedRepository: RecentlyVisitedRepository

    // API
    val searchApi: SearchApi
}
