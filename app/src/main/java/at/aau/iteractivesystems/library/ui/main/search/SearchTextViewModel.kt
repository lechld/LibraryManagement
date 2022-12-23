package at.aau.iteractivesystems.library.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.aau.iteractivesystems.library.ui.main.MainActivity

/**
 * Shared view model which is used by
 * [MainActivity] and [SearchDialogFragment] to share
 * current query string.
 */
class SearchTextViewModel : ViewModel() {

    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    fun setQuery(query: String?) {
        _query.postValue(query ?: "")
    }
}