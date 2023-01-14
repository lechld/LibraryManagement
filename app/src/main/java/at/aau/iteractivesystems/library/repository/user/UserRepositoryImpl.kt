package at.aau.iteractivesystems.library.repository.user

import at.aau.iteractivesystems.library.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl : UserRepository {

    /**
     * Usually we would store that persistently inside SharedPreferences or database,
     * for "fake project" like this it should be enough to only keep it inside
     * runtime memory.
     *
     * It's worth to think about improving this as we could fake registration flow
     * once we persist user somewhere.
     */
    private var user: User? = null

    private val observers = mutableListOf<UserRepository.Observer>()

    override suspend fun getUser(): User? = withContext(Dispatchers.IO) {
        user
    }

    override suspend fun setUser(user: User?) {
        this.user = user

        observers.forEach { it.userHasChanged(user) }
    }

    override fun addObserver(observer: UserRepository.Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: UserRepository.Observer) {
        observers.remove(observer)
    }
}