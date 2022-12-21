package at.aau.iteractivesystems.library.ui.main.discover.adapter

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemDiscoverSectionBinding

class DiscoverSectionViewHolder(
    private val binding: ItemDiscoverSectionBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val adapter = DiscoverSectionAdapter()

    fun bind(item: DiscoverElement.Section) {
        binding.title.text = item.title
        binding.recycler.adapter = adapter
        adapter.submitList(item.items)
    }
}