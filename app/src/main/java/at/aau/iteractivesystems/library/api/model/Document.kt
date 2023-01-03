package at.aau.iteractivesystems.library.api.model

import at.aau.iteractivesystems.library.api.COVERS_ENDPOINT
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Document(

    @SerialName("cover_i")
    val coverId: String? = null,

    @SerialName("edition_count")
    val editionCount: Int = 0,

    @SerialName("title")
    val title: String = "",

    @SerialName("author_name")
    val authorName: List<String> = emptyList(),

    @SerialName("first_publish_year")
    val firstPublishYear: String = "",

    @SerialName("key")
    val key: String = "",

    @SerialName("author_key")
    val authorKey: List<String> = emptyList()
) {
    val coverUrl: String? = if (coverId != null) COVERS_ENDPOINT + "id/$coverId-L.jpg" else null
}