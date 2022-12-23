package at.aau.iteractivesystems.library.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchDialogViewModel : ViewModel() {

    sealed class State {
        object Loading : State()
        data class Error(val error: Exception) : State()

        // TODO: Require some item structure which we show inside recycler later
        data class Loaded(val text: String) : State()
    }

    private val _state = MutableLiveData<State>(State.Loading)
    val state: LiveData<State>
        get() = _state

    private var query: String? = null

    fun submitQuery(query: String?) {
        if (this.query == query) {
            // Query has not been changed, ignore it
            return
        }

        // TODO: Perform search

        this.query = query
    }
}