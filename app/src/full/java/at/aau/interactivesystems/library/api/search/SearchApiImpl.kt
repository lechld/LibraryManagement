package at.aau.interactivesystems.library.api

import at.aau.iteractivesystems.library.api.OPEN_LIB_ENDPOINT
import at.aau.iteractivesystems.library.api.search.SearchApi
import at.aau.iteractivesystems.library.api.search.SearchResult
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val ENDPOINT = OPEN_LIB_ENDPOINT + "search.json"
private const val SEARCH_LIMIT = 100
private const val SUBJECT_LIMIT = 20

class SearchApiImpl(private val client: HttpClient) : SearchApi {

    override suspend fun searchByTitle(
        title: String
    ): SearchResult = withContext(Dispatchers.IO) {
        client.get(ENDPOINT) {
            parameter("title", title)
            parameter("limit", SEARCH_LIMIT)
        }
    }

    override suspend fun searchBySubject(
        subject: String
    ): SearchResult = withContext(Dispatchers.IO) {
        client.get(ENDPOINT) {
            parameter("subject", subject)
            parameter("limit", SUBJECT_LIMIT)
        }
    }
}