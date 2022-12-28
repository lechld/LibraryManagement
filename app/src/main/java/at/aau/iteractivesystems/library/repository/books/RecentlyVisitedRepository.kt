package at.aau.iteractivesystems.library.repository.books

interface RecentlyVisitedRepository {
    suspend fun getLatestBookIds(): List<String>
}