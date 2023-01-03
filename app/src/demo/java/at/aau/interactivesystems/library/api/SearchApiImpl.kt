package at.aau.interactivesystems.library.api

import at.aau.iteractivesystems.library.MOCK_LOADING_DELAY
import at.aau.iteractivesystems.library.api.model.Document
import at.aau.iteractivesystems.library.api.search.SearchApi
import at.aau.iteractivesystems.library.api.search.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.*

class SearchApiImpl : SearchApi {

    override suspend fun searchByTitle(
        title: String
    ): SearchResult = withContext(Dispatchers.IO) {
        delay(MOCK_LOADING_DELAY)

        SearchResult(
            0, 9, listOf(
                Document(null, 0, "$title 1", listOf("Author"), "1990", "key", listOf("authorKey")),
                Document(null, 0, "$title 2", listOf("Author"), "1990", "key", listOf("authorKey")),
                Document(null, 0, "$title 3", listOf("Author"), "1990", "key", listOf("authorKey")),
                Document(null, 0, "$title 4", listOf("Author"), "1990", "key", listOf("authorKey")),
                Document(null, 0, "$title 5", listOf("Author"), "1990", "key", listOf("authorKey")),
                Document(null, 0, "$title 6", listOf("Author"), "1990", "key", listOf("authorKey")),
                Document(null, 0, "$title 7", listOf("Author"), "1990", "key", listOf("authorKey")),
                Document(null, 0, "$title 8", listOf("Author"), "1990", "key", listOf("authorKey")),
                Document(null, 0, "$title 9", listOf("Author"), "1990", "key", listOf("authorKey")),
            )
        )
    }

    override suspend fun searchBySubject(
        subject: String
    ): SearchResult = withContext(Dispatchers.IO) {
        delay(MOCK_LOADING_DELAY)

        SearchResult(
            0, 9, listOf(
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$subject 1",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$subject 2",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$subject 3",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$subject 4",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$subject 5",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$subject 6",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$subject 7",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$subject 8",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$subject 9",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
            )
        )
    }
}