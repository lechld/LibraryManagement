package at.aau.iteractivesystems.library.ui.main.profile.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.aau.iteractivesystems.library.model.Gender
import at.aau.iteractivesystems.library.model.User
import at.aau.iteractivesystems.library.repository.user.UserRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class RegisterViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    fun register() {
        viewModelScope.launch {
            userRepository.setUser(
                User(
                    id = UUID.randomUUID().toString(),
                    firstName = "firstname",
                    lastName = "lastname",
                    birthday = LocalDate.now(),
                    gender = Gender.FEMALE
                )
            )
        }
    }
}