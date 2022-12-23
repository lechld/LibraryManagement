package at.aau.iteractivesystems.library

import at.aau.iteractivesystems.library.persistance.books.BooksRepository
import at.aau.iteractivesystems.library.persistance.books.RecentlyVisitedRepository
import at.aau.iteractivesystems.library.persistance.books.RecommendationRepository
import at.aau.iteractivesystems.library.persistance.user.UserRepository

/**
 * @see EnvironmentImpl
 */
interface Environment {
    val userRepository: UserRepository
    val booksRepository: BooksRepository
    val recommendationRepository: RecommendationRepository
    val recentlyVisitedRepository: RecentlyVisitedRepository
}
