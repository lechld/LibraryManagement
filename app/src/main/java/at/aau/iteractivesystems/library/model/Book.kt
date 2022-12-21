package at.aau.iteractivesystems.library.model

import java.time.LocalDate

data class Book(
    val isbn: String,
    val title: String,
    val image: String,
    val publicationDate: LocalDate
)