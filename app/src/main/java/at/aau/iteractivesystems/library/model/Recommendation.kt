package at.aau.iteractivesystems.library.model

import androidx.annotation.StringRes

data class Recommendation(
    val id: String,
    @StringRes val titleRes: Int,
    val books: List<Book>
)