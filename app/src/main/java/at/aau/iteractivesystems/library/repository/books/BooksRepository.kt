package at.aau.iteractivesystems.library.repository.books

import at.aau.iteractivesystems.library.model.Book

interface BooksRepository {
    suspend fun getBook(id: String): Book?
}