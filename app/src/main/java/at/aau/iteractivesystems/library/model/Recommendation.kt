package at.aau.iteractivesystems.library.model

data class Recommendation(
    val id: String,
    val title: String,
    val bookIds: List<String>
)