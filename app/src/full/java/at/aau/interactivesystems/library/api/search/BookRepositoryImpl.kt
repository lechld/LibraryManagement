package at.aau.interactivesystems.library.at.aau.interactivesystems.library.api.search

import at.aau.iteractivesystems.library.api.model.DocumentBookMapper
import at.aau.iteractivesystems.library.api.search.SearchApi
import at.aau.iteractivesystems.library.model.Book
import at.aau.iteractivesystems.library.model.Recommendation
import at.aau.iteractivesystems.library.model.Subject
import at.aau.iteractivesystems.library.repository.books.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class BookRepositoryImpl(
    private val searchApi: SearchApi,
    private val documentBookMapper: DocumentBookMapper = DocumentBookMapper
) : BookRepository {

    private val cachedBooks = hashMapOf<String, Book>()

    override suspend fun getBook(id: String): Book? = withContext(Dispatchers.IO) {
        return@withContext cachedBooks[id] // TODO: Introduce loading specific book via api
    }

    override suspend fun getRecommendations(): List<Recommendation> = withContext(Dispatchers.IO) {
        val recommendationsDeferred = Subject.values().map { subject ->
            async {
                val books = searchApi.searchBySubject(subject.name).docs.mapNotNull { doc ->
                    documentBookMapper.map(doc)
                }

                cacheBooks(books)

                Recommendation(
                    id = "recommendation-${subject.name}",
                    titleRes = subject.stringRes,
                    books = books,
                )
            }
        }

        return@withContext recommendationsDeferred.awaitAll()
    }

    override suspend fun search(query: String): List<Book> = withContext(Dispatchers.IO) {
        val searchResult = searchApi.searchByTitle(query)
        val books = searchResult.docs.mapNotNull { doc ->
            documentBookMapper.map(doc)
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