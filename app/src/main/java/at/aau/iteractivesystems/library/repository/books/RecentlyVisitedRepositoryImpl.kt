package at.aau.iteractivesystems.library.repository.books

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecentlyVisitedRepositoryImpl : RecentlyVisitedRepository {
    override suspend fun getLatestBookIds(): List<String> = withContext(Dispatchers.IO) {
        listOf("1", "2", "3", "4", "5", "6", "7")
    }
}