package at.aau.iteractivesystems.library.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import at.aau.iteractivesystems.library.ui.utils.SingleLiveEvent

class FloatingActionViewModel : ViewModel() {

    sealed class Action {
        object Add : Action()
        object Remove : Action()
        object Hidden : Action()
    }

    private val _action = SingleLiveEvent<Action>(Action.Hidden)
    val action: LiveData<Action>
        get() = _action

    fun setAction(action: Action) {
        _action.postValue(action)
    }

    private val _selected = SingleLiveEvent(Unit)
    val selected: LiveData<Unit>
        get() = _selected

    fun setSelected() {
        _selected.postValue(Unit)
    }
}