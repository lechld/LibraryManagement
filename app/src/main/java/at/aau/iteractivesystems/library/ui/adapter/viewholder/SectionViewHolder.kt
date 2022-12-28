package at.aau.iteractivesystems.library.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemSectionBinding
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.adapter.viewholder.section.BigSectionAdapter
import at.aau.iteractivesystems.library.ui.adapter.viewholder.section.SmallSectionAdapter

class SectionViewHolder(
    private val binding: ItemSectionBinding,
    onClick: (Content.Section.Item) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private val bigAdapter = BigSectionAdapter(onClick)
    private val smallAdapter = SmallSectionAdapter(onClick)

    fun bind(item: Content.Section) {
        val adapter = if (item is Content.Section.Big) bigAdapter else smallAdapter

        binding.recycler.adapter = adapter
        adapter.submitList(item.items)
    }
}