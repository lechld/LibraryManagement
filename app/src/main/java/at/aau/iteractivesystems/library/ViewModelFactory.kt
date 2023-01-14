package at.aau.iteractivesystems.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.aau.iteractivesystems.library.ui.main.FloatingActionViewModel
import at.aau.iteractivesystems.library.ui.main.explore.ExploreViewModel
import at.aau.iteractivesystems.library.ui.main.home.HomeViewModel
import at.aau.iteractivesystems.library.ui.main.profile.ProfileViewModel
import at.aau.iteractivesystems.library.ui.main.profile.login.LoginViewModel
import at.aau.iteractivesystems.library.ui.main.profile.register.RegisterViewModel
import at.aau.iteractivesystems.library.ui.main.search.SearchDialogViewModel
import at.aau.iteractivesystems.library.ui.main.search.SearchTextViewModel

class ViewModelFactory(
    private val environment: Environment
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // Can safely ignore that warning, we check isAssignableFrom
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ExploreViewModel::class.java) -> (ExploreViewModel(
                recommendationRepository = environment.recommendationRepository,
            )) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> (HomeViewModel()) as T
            modelClass.isAssignableFrom(SearchDialogViewModel::class.java) -> (SearchDialogViewModel(
                searchApi = environment.searchApi,
            )) as T
            modelClass.isAssignableFrom(SearchTextViewModel::class.java) -> (SearchTextViewModel()) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> (LoginViewModel(
                userRepository = environment.userRepository,
            )) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> (ProfileViewModel(
                userRepository = environment.userRepository,
            )) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> (RegisterViewModel(
                userRepository = environment.userRepository,
            )) as T
            modelClass.isAssignableFrom(FloatingActionViewModel::class.java) -> (FloatingActionViewModel()) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class. Maybe forgot to register it in ViewModelFactory?")
        }
    }
}