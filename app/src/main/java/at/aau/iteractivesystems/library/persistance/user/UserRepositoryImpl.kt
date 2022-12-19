package at.aau.iteractivesystems.library.persistance.user

import at.aau.iteractivesystems.library.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    override suspend fun getUser(): User? = withContext(Dispatchers.IO) {
        delay(500L) // fake loading takes some time off main thread

        user
    }

    override suspend fun setUser(user: User?) {
        this.user = user
    }
}