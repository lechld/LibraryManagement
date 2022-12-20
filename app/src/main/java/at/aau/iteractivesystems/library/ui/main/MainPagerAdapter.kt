package at.aau.iteractivesystems.library.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import at.aau.iteractivesystems.library.ui.main.borrowed.BorrowedBooksFragment
import at.aau.iteractivesystems.library.ui.main.discover.DiscoverFragment

class MainPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DiscoverFragment() // TODO: Should not use constructor here
            1 -> BorrowedBooksFragment()
            else -> throw IllegalStateException("MainPagerAdapter supports only two fragments")
        }
    }
}