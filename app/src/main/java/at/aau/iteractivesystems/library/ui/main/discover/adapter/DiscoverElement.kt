package at.aau.iteractivesystems.library.ui.main.discover.adapter

import androidx.annotation.StringRes

sealed class DiscoverElement {

    data class Text(
        @StringRes
        val stringRes: Int
    ) : DiscoverElement()

    data class Section(
        val id: String,
        val title: String,
        val items: List<Item>
    ) : DiscoverElement() {

        data class Item(
            val id: String,
            val image: String,
            val title: String
        )
    }
}