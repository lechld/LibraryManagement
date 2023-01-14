package at.aau.iteractivesystems.library.model

import androidx.annotation.StringRes
import at.aau.iteractivesystems.library.R

/**
 * Represents some subjects of a book.
 * Inspired by openlibrary.org/subjects
 */
enum class Subject(@StringRes val stringRes: Int) {
    ROMANCE(R.string.subject_romance),
    THRILLER(R.string.subject_thriller),
    KIDS(R.string.subject_kids),
    FANTASY(R.string.subject_fantasy),
    PROGRAMMING(R.string.subject_programming),
    FINANCE(R.string.subject_finance),
    HISTORY(R.string.subject_history),
    PSYCHOLOGY(R.string.subject_psychology),
    OTHER(R.string.subject_other);
}