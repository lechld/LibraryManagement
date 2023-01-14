package at.aau.iteractivesystems.library.repository.books

import at.aau.iteractivesystems.library.model.Book
import at.aau.iteractivesystems.library.model.Recommendation

interface BookRepository {
    suspend fun getBook(id: String): Book?
    suspend fun getRecommendations(): List<Recommendation>
    suspend fun search(query: String): List<Book>
}