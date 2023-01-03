package at.aau.iteractivesystems.library.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import at.aau.iteractivesystems.library.databinding.ViewLoadingBinding

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        ViewLoadingBinding.inflate(LayoutInflater.from(context), this, true)
    }
}