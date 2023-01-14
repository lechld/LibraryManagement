package at.aau.iteractivesystems.library

import at.aau.iteractivesystems.library.api.search.SearchApi
import at.aau.iteractivesystems.library.repository.books.BookRepository
import at.aau.iteractivesystems.library.repository.books.BorrowedBooksRepository
import at.aau.iteractivesystems.library.repository.user.UserRepository

/**
 * @see EnvironmentImpl
 */
interface Environment {
    val userRepository: UserRepository
    val borrowedBooksRepository: BorrowedBooksRepository
    val bookRepository: BookRepository
}
