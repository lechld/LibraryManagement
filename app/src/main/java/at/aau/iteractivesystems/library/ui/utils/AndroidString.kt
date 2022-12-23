package at.aau.iteractivesystems.library.ui.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class AndroidString {

    data class Resource(@StringRes val id: Int) : AndroidString()

    data class Text(val string: String) : AndroidString()

    fun asString(context: Context): String = when (this) {
        is Resource -> context.getString(this.id)
        is Text -> this.string
    }
}