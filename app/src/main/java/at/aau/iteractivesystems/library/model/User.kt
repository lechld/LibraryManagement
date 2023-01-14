package at.aau.iteractivesystems.library.model

import java.time.LocalDate

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: LocalDate,
    val gender: Gender,
)

val DUMMY_USER = User("userId", "John", "Doe", LocalDate.of(1990, 12, 20), Gender.MALE)