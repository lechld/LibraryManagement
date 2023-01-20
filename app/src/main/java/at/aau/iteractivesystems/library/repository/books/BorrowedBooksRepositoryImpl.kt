package at.aau.iteractivesystems.library.repository.books

class BorrowedBooksRepositoryImpl : BorrowedBooksRepository {

    private val bookIds = mutableSetOf<String>()

    private val observers = mutableSetOf<BorrowedBooksRepository.Observer>()

    override fun add(bookId: String) {
        bookIds.add(bookId)
        observers.forEach { it.borrowedStateChanged() }
    }

    override fun remove(bookId: String) {
        bookIds.remove(bookId)
        observers.forEach { it.borrowedStateChanged() }
    }

    override fun contains(bookId: String): Boolean {
        return bookIds.contains(bookId)
    }

    override fun getAll(): Set<String> {
        return bookIds
    }

    override fun addObserver(observer: BorrowedBooksRepository.Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: BorrowedBooksRepository.Observer) {
        observers.remove(observer)
    }
}