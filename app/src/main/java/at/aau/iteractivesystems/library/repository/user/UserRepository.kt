package at.aau.iteractivesystems.library.repository.user

import at.aau.iteractivesystems.library.model.User

interface UserRepository {
    suspend fun getUser(): User?
    suspend fun setUser(user: User?)
}