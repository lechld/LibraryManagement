package at.aau.iteractivesystems.library.api.search

import at.aau.iteractivesystems.library.api.model.Document
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SearchResult(

    @SerialName("start")
    val start: Int,

    @SerialName("num_found")
    val numFound: Int,

    @SerialName("docs")
    val docs: List<Document>
)