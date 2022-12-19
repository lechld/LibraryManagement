package at.aau.iteractivesystems.library.ui.startup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.persistance.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartupViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    /**
     * Sealed class behaves similar to enumeration, except that can
     * use real subclasses (which can hold data if needed)
     */
    sealed class State {
        object Loading : State()
        object NotLoggedIn : State()
        object LoggedIn : State()
    }

    private val _state = MutableLiveData<State>(State.Loading) // Initially use Loading
    val state: LiveData<State> // Only expose non-mutable property to users of this class
        get() = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // fake some loading operation, we are on some IO thread so we don't block main thread here.
            delay(500L)

            val currentUser = userRepository.getUser()

            // Check if we are logged in and update state accordingly
            if (currentUser == null) {
                // be aware, postValue ensures that value is posted on main thread, we don't need to think about that more.
                _state.postValue(State.NotLoggedIn)
            } else {
                _state.postValue(State.LoggedIn)
            }
        }
    }
}