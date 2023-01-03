package at.aau.iteractivesystems.library.ui.adapter.staterestoration

import androidx.recyclerview.widget.RecyclerView

interface NestedRecyclerViewViewHolder {
    fun getId(): String
    fun getLayoutManager(): RecyclerView.LayoutManager?
}