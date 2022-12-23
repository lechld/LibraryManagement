package at.aau.iteractivesystems.library.api.search

import at.aau.iteractivesystems.library.api.OPEN_LIB_ENDPOINT
import at.aau.iteractivesystems.library.api.model.search.SearchResult
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val ENDPOINT = OPEN_LIB_ENDPOINT + "search.json"

class SearchApiImpl(private val client: HttpClient) : SearchApi {

    override suspend fun searchByTitle(
        title: String
    ): SearchResult = withContext(Dispatchers.IO) {
        client.get(ENDPOINT) {
            parameter("title", title)
            parameter("limit", 100)
        }
    }
}