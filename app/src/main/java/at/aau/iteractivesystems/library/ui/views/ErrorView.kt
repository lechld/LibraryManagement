package at.aau.iteractivesystems.library.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.databinding.ViewErrorBinding
import java.io.IOException

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    var reloadListener: (() -> Unit?)? = null
        set(value) {
            field = value
            binding.retryButton.setOnClickListener { value?.invoke() }
        }

    private val binding: ViewErrorBinding =
        ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)

    fun setError(error: Throwable?) {
        val errorRes = if (error is IOException) {
            R.string.connection_error_message
        } else {
            R.string.default_error_message
        }

        binding.errorText.setText(errorRes)
        isVisible = error != null
    }
}