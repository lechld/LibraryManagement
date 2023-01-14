package at.aau.iteractivesystems.library.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val coverUrl: String,
    val subject: Subject,
    val publicationYear: String,
)