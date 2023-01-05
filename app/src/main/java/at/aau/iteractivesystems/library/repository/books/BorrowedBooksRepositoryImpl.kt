package at.aau.iteractivesystems.library.repository.books

class BorrowedBooksRepositoryImpl : BorrowedBooksRepository {

    private val bookIds = mutableSetOf<String>()

    override fun add(bookId: String) {
        bookIds.add(bookId)
    }

    override fun remove(bookId: String) {
        bookIds.remove(bookId)
    }

    override fun contains(bookId: String): Boolean {
        return bookIds.contains(bookId)
    }
}