package at.aau.iteractivesystems.library.ui.utils

sealed class ViewState<out T>(val data: T?) {
    class Success<T>(data: T? = null) : ViewState<T>(data)
    class Failure<T>(val exception: Throwable, data: T? = null) : ViewState<T>(data)
    class Loading<T>(data: T? = null) : ViewState<T>(data)
}
