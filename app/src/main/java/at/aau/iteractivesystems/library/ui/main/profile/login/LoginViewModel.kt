package at.aau.iteractivesystems.library.ui.main.profile.login

import androidx.lifecycle.ViewModel
import at.aau.iteractivesystems.library.model.DUMMY_USER
import at.aau.iteractivesystems.library.repository.user.UserRepository

class LoginViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    suspend fun login(mail: String, password: String): Result<Unit> {
        if (mail.isBlank() || password.isBlank()) {
            return Result.failure(IllegalStateException("Invalid input"))
        }

        userRepository.setUser(DUMMY_USER)

        return Result.success(Unit)
    }
}