package at.aau.iteractivesystems.library.api.model

import at.aau.iteractivesystems.library.model.Book
import at.aau.iteractivesystems.library.model.Subject

object DocumentBookMapper {

    fun map(document: Document): Book? {
        val authorName = document.authorName.firstOrNull()
            ?: return null // assume first author is nice enough
        val coverUrl = document.coverUrl ?: return null
        val subject = Subject.values().find {
            document.subject.contains(it.name)
        } ?: Subject.OTHER

        return Book(
            id = document.id,
            title = document.title,
            author = authorName,
            coverUrl = coverUrl,
            subject = subject,
            publicationYear = document.firstPublishYear
        )
    }
}