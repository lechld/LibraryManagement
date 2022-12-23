package at.aau.iteractivesystems.library.ui.main.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemSearchBinding
import at.aau.iteractivesystems.library.ui.main.adapter.Content

class SearchViewHolder(
    private val binding: ItemSearchBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Content.Search) {
        binding.seachView.setQuery(item.text, false)
    }
}