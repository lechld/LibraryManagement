package at.aau.iteractivesystems.library.persistance.books

import at.aau.iteractivesystems.library.model.Book

interface BooksRepository {
    suspend fun getBook(id: String): Book?
}