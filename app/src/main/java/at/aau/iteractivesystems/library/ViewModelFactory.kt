package at.aau.iteractivesystems.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.aau.iteractivesystems.library.ui.login.LoginViewModel
import at.aau.iteractivesystems.library.ui.main.MainViewModel
import at.aau.iteractivesystems.library.ui.main.borrowed.BorrowedBooksViewModel
import at.aau.iteractivesystems.library.ui.main.discover.DiscoverViewModel
import at.aau.iteractivesystems.library.ui.main.reserved.ReservedViewModel
import at.aau.iteractivesystems.library.ui.profile.ProfileViewModel
import at.aau.iteractivesystems.library.ui.startup.StartupViewModel

class ViewModelFactory(
    private val environment: Environment
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // Can safely ignore that warning, we check isAssignableFrom
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(StartupViewModel::class.java) -> (StartupViewModel(
                userRepository = environment.userRepository
            )) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> (LoginViewModel()) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> (MainViewModel()) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> (ProfileViewModel()) as T
            modelClass.isAssignableFrom(DiscoverViewModel::class.java) -> (DiscoverViewModel(
                recommendationRepository = environment.recommendationRepository,
                booksRepository = environment.booksRepository
            )) as T
            modelClass.isAssignableFrom(BorrowedBooksViewModel::class.java) -> (BorrowedBooksViewModel()) as T
            modelClass.isAssignableFrom(ReservedViewModel::class.java) -> (ReservedViewModel()) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class. Maybe forgot to register it in ViewModelFactory?")
        }
    }
}