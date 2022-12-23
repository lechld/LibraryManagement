package at.aau.iteractivesystems.library.persistance.books

import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.api.model.search.Document
import at.aau.iteractivesystems.library.api.search.SearchApi
import at.aau.iteractivesystems.library.model.Recommendation
import at.aau.iteractivesystems.library.model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class RecommendationRepositoryImpl(
    private val searchApi: SearchApi,
) : RecommendationRepository {

    override suspend fun getRecommendations(): List<Recommendation> = withContext(Dispatchers.IO) {
        val romanceDeferred = async { searchApi.searchBySubject(Subject.ROMANCE.name) }
        val thrillerDeferred = async { searchApi.searchBySubject(Subject.THRILLER.name) }
        val kidsDeferred = async { searchApi.searchBySubject(Subject.KIDS.name) }
        val fantasyDeferred = async { searchApi.searchBySubject(Subject.FANTASY.name) }
        val programmingDeferred = async { searchApi.searchBySubject(Subject.PROGRAMMING.name) }
        val historyDeferred = async { searchApi.searchBySubject(Subject.HISTORY.name) }
        val psychologyDeferred = async { searchApi.searchBySubject(Subject.PSYCHOLOGY.name) }
        // using async (return type deferred) allows us to execute all calls in parallel

        val romance = romanceDeferred.await()
        val thriller = thrillerDeferred.await()
        val kids = kidsDeferred.await()
        val fantasy = fantasyDeferred.await()
        val programming = programmingDeferred.await()
        val history = historyDeferred.await()
        val psychology = psychologyDeferred.await()
        // await() ensures that we wait until all of them are completed

        val result = mutableListOf<Recommendation>()

        result.add(
            Recommendation(
                id = "romance",
                titleRes = R.string.subject_romance,
                items = mapItems(romance.docs)
            )
        )

        result.add(
            Recommendation(
                id = "thriller",
                titleRes = R.string.subject_thriller,
                items = mapItems(thriller.docs)
            )
        )

        result.add(
            Recommendation(
                id = "kids",
                titleRes = R.string.subject_kids,
                items = mapItems(kids.docs)
            )
        )

        result.add(
            Recommendation(
                id = "fantasy",
                titleRes = R.string.subject_fantasy,
                items = mapItems(fantasy.docs)
            )
        )

        result.add(
            Recommendation(
                id = "programming",
                titleRes = R.string.subject_programming,
                items = mapItems(programming.docs)
            )
        )

        result.add(
            Recommendation(
                id = "history",
                titleRes = R.string.subject_history,
                items = mapItems(history.docs)
            )
        )

        result.add(
            Recommendation(
                id = "psychology",
                titleRes = R.string.subject_psychology,
                items = mapItems(psychology.docs)
            )
        )

        return@withContext result
    }

    private fun mapItems(docs: List<Document>): List<Recommendation.Item> {
        return docs.mapNotNull { document ->
            val coverId = document.coverId ?: return@mapNotNull null
            val authorName = document.authorName?.firstOrNull() ?: return@mapNotNull null // assume first author is nice enough

            Recommendation.Item(coverId, document.title, authorName)
        }
    }
}