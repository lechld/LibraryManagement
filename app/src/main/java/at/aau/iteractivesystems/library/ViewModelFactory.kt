package at.aau.iteractivesystems.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.aau.iteractivesystems.library.ui.main.explore.ExploreViewModel
import at.aau.iteractivesystems.library.ui.main.home.HomeViewModel
import at.aau.iteractivesystems.library.ui.main.profile.ProfileViewModel
import at.aau.iteractivesystems.library.ui.main.profile.login.LoginViewModel
import at.aau.iteractivesystems.library.ui.main.profile.register.RegisterViewModel
import at.aau.iteractivesystems.library.ui.main.search.SearchViewModel

class ViewModelFactory(
    private val environment: Environment
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // Can safely ignore that warning, we check isAssignableFrom
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ExploreViewModel::class.java) -> (ExploreViewModel(
                bookRepository = environment.bookRepository,
            )) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> (HomeViewModel(
                bookRepository = environment.bookRepository,
                borrowedBooksRepository = environment.borrowedBooksRepository,
            )) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> (SearchViewModel(
                bookRepository = environment.bookRepository,
            )) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> (LoginViewModel()) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> (ProfileViewModel()) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> (RegisterViewModel()) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class. Maybe forgot to register it in ViewModelFactory?")
        }
    }
}