package at.aau.interactivesystems.library.repository.books

import at.aau.iteractivesystems.library.MOCK_LOADING_DELAY
import at.aau.iteractivesystems.library.repository.books.RecentlyVisitedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RecentlyVisitedRepositoryImpl : RecentlyVisitedRepository {
    override suspend fun getLatestBookIds(): List<String> = withContext(Dispatchers.IO) {
        delay(MOCK_LOADING_DELAY)
        listOf("1", "2", "3", "4", "5", "6", "7")
    }
}