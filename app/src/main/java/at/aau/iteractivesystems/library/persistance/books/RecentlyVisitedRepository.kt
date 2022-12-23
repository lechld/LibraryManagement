package at.aau.iteractivesystems.library.persistance.books

interface RecentlyVisitedRepository {
    suspend fun getLatestBookIds(): List<String>
}