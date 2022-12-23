package at.aau.iteractivesystems.library.model

import androidx.annotation.StringRes

data class Recommendation(
    val id: String,
    @StringRes
    val titleRes: Int,
    val items: List<Item>
) {
    data class Item(
        val coverId: String,
        val title: String,
        val author: String,
    )
}