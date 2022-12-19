package at.aau.iteractivesystems.library.persistance.user

import at.aau.iteractivesystems.library.model.User

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

    override fun getUser(): User? {
        return user
    }

    override fun setUser(user: User?) {
        this.user = user
    }
}