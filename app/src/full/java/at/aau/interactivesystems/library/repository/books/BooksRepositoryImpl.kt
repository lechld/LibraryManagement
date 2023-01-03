package at.aau.interactivesystems.library.repository.books

import at.aau.iteractivesystems.library.model.Book
import at.aau.iteractivesystems.library.repository.books.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class BooksRepositoryImpl : BooksRepository {

    override suspend fun getBook(id: String): Book = withContext(Dispatchers.IO) {
        Book(id, "ReleaseBook", "imageUrl", LocalDate.of(1993, 10, 6))
    }
}