package at.aau.iteractivesystems.library.model

data class Library(
    val id: String,
    val name: String,
    val address: String, // TODO: Could optimize using specific address type containing zip, street,..
)