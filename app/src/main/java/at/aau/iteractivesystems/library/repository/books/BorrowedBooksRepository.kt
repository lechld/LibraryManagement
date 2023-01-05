package at.aau.iteractivesystems.library.repository.books

interface BorrowedBooksRepository {
    fun add(bookId: String)
    fun remove(bookId: String)
    fun contains(bookId: String): Boolean
}