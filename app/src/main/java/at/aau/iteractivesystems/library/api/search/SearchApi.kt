package at.aau.iteractivesystems.library.api.search

interface SearchApi {
    suspend fun searchByTitle(title: String): SearchResult
    suspend fun searchBySubject(subject: String): SearchResult
}