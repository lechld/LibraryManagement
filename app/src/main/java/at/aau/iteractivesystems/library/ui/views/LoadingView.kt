package at.aau.iteractivesystems.library.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import at.aau.iteractivesystems.library.databinding.ViewLoadingBinding

private const val DELAY_SHOW_MS: Long = 100
private const val DELAY_HIDE_MS: Long = 400

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var targetVisibility = View.GONE

    private val showRunnable = Runnable {
        if (targetVisibility != View.VISIBLE) {
            return@Runnable
        }
        visibility = View.VISIBLE
    }

    private val hideRunnable = Runnable {
        if (targetVisibility != View.GONE) {
            return@Runnable
        }
        visibility = View.GONE
    }

    init {
        ViewLoadingBinding.inflate(LayoutInflater.from(context), this, true)
    }

    @Override
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks()
    }

    @Synchronized
    fun show() {
        removeCallbacks()
        targetVisibility = View.VISIBLE

        if (visibility == View.VISIBLE) {
            return
        }

        postDelayed(showRunnable, DELAY_SHOW_MS)
    }

    @Synchronized
    fun hide() {
        removeCallbacks()
        targetVisibility = View.GONE

        if (visibility == View.GONE) {
            return
        }

        postDelayed(hideRunnable, DELAY_HIDE_MS)
    }

    private fun removeCallbacks() {
        removeCallbacks(showRunnable)
        removeCallbacks(hideRunnable)
    }
}