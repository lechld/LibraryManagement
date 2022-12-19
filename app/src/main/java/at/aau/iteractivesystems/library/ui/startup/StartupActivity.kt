package at.aau.iteractivesystems.library.ui.startup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import at.aau.iteractivesystems.library.R

/**
 * That's the main and startup activity.
 * It's view is a [FragmentContainerView] which hosts
 * all screens used by the application.
 *
 * We try follow Single-Activity-Architecture and use Navigation Component library
 * to handle transition and navigation between screens.
 */
class StartupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)
    }
}