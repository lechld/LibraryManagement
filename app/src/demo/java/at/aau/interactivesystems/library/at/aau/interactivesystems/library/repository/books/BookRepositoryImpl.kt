package at.aau.interactivesystems.library.at.aau.interactivesystems.library.repository.books

import at.aau.iteractivesystems.library.model.Book
import at.aau.iteractivesystems.library.model.Recommendation
import at.aau.iteractivesystems.library.model.Subject
import at.aau.iteractivesystems.library.repository.books.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val MOCK_LOADING_DELAY = 2000L

class BookRepositoryImpl : BookRepository {

    private val cachedBooks = hashMapOf<String, Book>()

    override suspend fun getBook(id: String): Book? = withContext(Dispatchers.IO) {
        return@withContext cachedBooks[id]
    }

    override suspend fun getRecommendations(): List<Recommendation> = withContext(Dispatchers.IO) {
        delay(MOCK_LOADING_DELAY)

        return@withContext Subject.values().map { subject ->
            val books = mutableListOf<Book>()

            repeat(15) { counter ->
                val randomBook = Book(
                    id = "$subject-$counter",
                    title = "Book ${subject.name} $counter",
                    author = "Author",
                    coverUrl = "",
                    subject = subject,
                    publicationYear = (1960..2023).random().toString()
                )

                books.add(randomBook)
            }

            cacheBooks(books)

            Recommendation("recommendation-$subject", subject.stringRes, books)
        }
    }

    override suspend fun search(query: String): List<Book> = withContext(Dispatchers.IO) {
        delay(MOCK_LOADING_DELAY)

        val books = mutableListOf<Book>()

        repeat(15) { counter ->
            val randomBook = Book(
                id = "$query-$counter",
                title = "Book $query $counter",
                author = "Author",
                coverUrl = "",
                subject = Subject.values().random(),
                publicationYear = (1960..2023).random().toString()
            )

            books.add(randomBook)
        }

        cacheBooks(books)

        return@withContext books
    }

    private fun cacheBooks(books: List<Book>) {
        books.forEach { book ->
            cachedBooks[book.id] = book
        }
    }
}