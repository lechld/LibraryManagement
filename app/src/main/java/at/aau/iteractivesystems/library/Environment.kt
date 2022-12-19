package at.aau.iteractivesystems.library

import at.aau.iteractivesystems.library.persistance.user.UserRepository

/**
 * @see EnvironmentImpl
 */
interface Environment {
    val userRepository: UserRepository
}
