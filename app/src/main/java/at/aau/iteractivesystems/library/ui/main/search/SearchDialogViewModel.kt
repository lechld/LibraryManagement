package at.aau.iteractivesystems.library.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.api.search.SearchApi
import kotlinx.coroutines.launch

class SearchDialogViewModel(
    private val searchApi: SearchApi,
) : ViewModel() {

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
        if (this.query == query) return

        if (query.isNullOrEmpty()) {
            this.query = query
            // TODO: Cleanup result list
            return
        }

        _state.postValue(State.Loading)

        viewModelScope.launch {
            try {
                val searchResult = searchApi.searchByTitle(query)

                print(searchResult.docs)

                _state.postValue(State.Loaded("dummy"))

            } catch (error: Exception) {
                _state.postValue(State.Error(error))
            }

            this@SearchDialogViewModel.query = query
        }
    }
}