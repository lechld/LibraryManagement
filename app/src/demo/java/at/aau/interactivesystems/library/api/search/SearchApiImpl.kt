package at.aau.interactivesystems.library.at.aau.interactivesystems.library.api.search

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
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$title 1",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$title 2",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$title 3",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$title 4",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$title 5",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$title 6",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$title 7",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$title 8",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
                Document(
                    coverId = UUID.randomUUID().toString(),
                    editionCount = 0,
                    title = "$title 9",
                    authorName = listOf("Author"),
                    firstPublishYear = "1990",
                    key = "key",
                    authorKey = listOf("authorKey")
                ),
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