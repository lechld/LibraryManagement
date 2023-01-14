package at.aau.interactivesystems.library

import at.aau.interactivesystems.library.at.aau.interactivesystems.library.api.search.BookRepositoryImpl
import at.aau.iteractivesystems.library.Environment
import at.aau.iteractivesystems.library.api.ktorHttpClient
import at.aau.iteractivesystems.library.api.search.SearchApi
import at.aau.iteractivesystems.library.api.search.SearchApiImpl
import at.aau.iteractivesystems.library.repository.books.BookRepository
import at.aau.iteractivesystems.library.repository.books.BorrowedBooksRepository
import at.aau.iteractivesystems.library.repository.books.BorrowedBooksRepositoryImpl
import at.aau.iteractivesystems.library.repository.user.UserRepository
import at.aau.iteractivesystems.library.repository.user.UserRepositoryImpl

object EnvironmentImpl : Environment {

    private val searchApi: SearchApi by lazy {
        SearchApiImpl(ktorHttpClient)
    }

    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl()
    }

    override val borrowedBooksRepository: BorrowedBooksRepository by lazy {
        BorrowedBooksRepositoryImpl()
    }

    override val bookRepository: BookRepository by lazy {
        BookRepositoryImpl(searchApi)
    }

}