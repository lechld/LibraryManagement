package at.aau.iteractivesystems.library.ui.adapter

import at.aau.iteractivesystems.library.ui.utils.AndroidString

sealed class Content {

    data class Headline(
        val text: AndroidString,
    ) : Content()

    data class HeadlineSmall(
        val text: AndroidString,
    ) : Content()

    sealed class Section : Content() {
        abstract val id: String
        abstract val items: List<Item>

        data class Big(
            override val id: String,
            override val items: List<Item>,
        ) : Section()

        data class Small(
            override val id: String,
            override val items: List<Item>
        ) : Section()

        data class Item(
            val id: String,
            val imageUrl: String?,
            val title: String,
        ) : Content()
    }

    data class SearchResult(
        val id: String,
        val imageUrl: String?,
        val title: String,
        val author: String,
        val publicationYear: String
    ) : Content()

    data class Detail(
        val id: String,
        val imageUrl: String?,
    ) : Content()
}