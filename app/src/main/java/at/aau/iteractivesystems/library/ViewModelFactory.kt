package at.aau.iteractivesystems.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.aau.iteractivesystems.library.ui.login.LoginViewModel
import at.aau.iteractivesystems.library.ui.main.explore.ExploreViewModel
import at.aau.iteractivesystems.library.ui.main.home.HomeViewModel
import at.aau.iteractivesystems.library.ui.main.search.SearchDialogViewModel
import at.aau.iteractivesystems.library.ui.main.search.SearchTextViewModel
import at.aau.iteractivesystems.library.ui.main.profile.ProfileViewModel

class ViewModelFactory(
    private val environment: Environment
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // Can safely ignore that warning, we check isAssignableFrom
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> (LoginViewModel()) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> (ProfileViewModel()) as T
            modelClass.isAssignableFrom(ExploreViewModel::class.java) -> (ExploreViewModel(
                recommendationRepository = environment.recommendationRepository,
                booksRepository = environment.booksRepository,
                recentlyVisitedRepository = environment.recentlyVisitedRepository,
            )) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> (HomeViewModel()) as T
            modelClass.isAssignableFrom(SearchDialogViewModel::class.java) -> (SearchDialogViewModel()) as T
            modelClass.isAssignableFrom(SearchTextViewModel::class.java) -> (SearchTextViewModel()) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class. Maybe forgot to register it in ViewModelFactory?")
        }
    }
}