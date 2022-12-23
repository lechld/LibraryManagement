package at.aau.iteractivesystems.library.ui.main.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemSectionBinding
import at.aau.iteractivesystems.library.ui.main.adapter.Content
import at.aau.iteractivesystems.library.ui.main.adapter.viewholder.section.BigSectionAdapter
import at.aau.iteractivesystems.library.ui.main.adapter.viewholder.section.SmallSectionAdapter

class SectionViewHolder(
    private val binding: ItemSectionBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val bigAdapter = BigSectionAdapter()
    private val smallAdapter = SmallSectionAdapter()

    fun bind(item: Content.Section) {
        val adapter = if (item is Content.Section.Big) bigAdapter else smallAdapter

        binding.recycler.adapter = adapter
        adapter.submitList(item.items)
    }
}