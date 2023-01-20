package at.aau.iteractivesystems.library.repository.books

interface BorrowedBooksRepository {
    fun add(bookId: String)
    fun remove(bookId: String)
    fun contains(bookId: String): Boolean
    fun getAll(): Set<String>

    fun addObserver(observer: Observer)
    fun removeObserver(observer: Observer)

    interface Observer {
        fun borrowedStateChanged()
    }
}