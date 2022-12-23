package at.aau.iteractivesystems.library.api.search

import at.aau.iteractivesystems.library.api.model.search.SearchResult

interface SearchApi {
    suspend fun searchByTitle(title: String): SearchResult
}