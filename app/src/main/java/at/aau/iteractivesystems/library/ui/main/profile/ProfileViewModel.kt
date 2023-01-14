package at.aau.iteractivesystems.library.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.model.User
import at.aau.iteractivesystems.library.repository.user.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository,
) : ViewModel(), UserRepository.Observer {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    init {
        viewModelScope.launch {
            _isLoggedIn.postValue(userRepository.getUser() != null)
        }

        userRepository.addObserver(this)
    }

    override fun onCleared() {
        userRepository.removeObserver(this)
        super.onCleared()
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.setUser(null)
        }
    }

    override fun userHasChanged(user: User?) {
        _isLoggedIn.postValue(user != null)
    }
}