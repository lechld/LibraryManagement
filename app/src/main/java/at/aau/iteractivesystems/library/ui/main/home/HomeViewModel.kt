package at.aau.iteractivesystems.library.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.utils.ViewState

class HomeViewModel : ViewModel() {

    // TODO: Change generic type to whatever needed in this screen
    private val _state: MutableLiveData<ViewState<List<Content>>> =
        MutableLiveData(ViewState.Loading())

    val state: LiveData<ViewState<List<Content>>>
        get() = _state

}