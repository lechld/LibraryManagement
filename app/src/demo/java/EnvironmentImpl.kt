package at.aau.interactivesystems.library

import at.aau.interactivesystems.library.at.aau.interactivesystems.library.repository.books.BookRepositoryImpl
import at.aau.iteractivesystems.library.Environment
import at.aau.iteractivesystems.library.repository.books.BookRepository
import at.aau.iteractivesystems.library.repository.books.BorrowedBooksRepository
import at.aau.iteractivesystems.library.repository.books.BorrowedBooksRepositoryImpl
import at.aau.iteractivesystems.library.repository.user.UserRepository
import at.aau.iteractivesystems.library.repository.user.UserRepositoryImpl

object EnvironmentImpl : Environment {
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl()
    }

    override val borrowedBooksRepository: BorrowedBooksRepository by lazy {
        BorrowedBooksRepositoryImpl()
    }

    override val bookRepository: BookRepository by lazy {
        BookRepositoryImpl()
    }
}