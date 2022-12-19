package at.aau.iteractivesystems.library.persistance.user

import at.aau.iteractivesystems.library.model.User

interface UserRepository {
    fun getUser(): User?
    fun setUser(user: User?)
}