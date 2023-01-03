package at.aau.iteractivesystems.library.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemSectionBinding
import at.aau.iteractivesystems.library.ui.adapter.Content
import at.aau.iteractivesystems.library.ui.adapter.staterestoration.NestedRecyclerViewViewHolder
import at.aau.iteractivesystems.library.ui.adapter.viewholder.section.BigSectionAdapter
import at.aau.iteractivesystems.library.ui.adapter.viewholder.section.SmallSectionAdapter

class SectionViewHolder(
    private val binding: ItemSectionBinding,
    onClick: (Content.Section.Item) -> Unit,
) : RecyclerView.ViewHolder(binding.root), NestedRecyclerViewViewHolder {

    private var section: Content.Section? = null

    private val bigAdapter = BigSectionAdapter(onClick)
    private val smallAdapter = SmallSectionAdapter(onClick)

    init {
        binding.recycler.itemAnimator = null
    }

    fun bind(item: Content.Section) {
        this.section = item

        val adapter = if (item is Content.Section.Big) bigAdapter else smallAdapter

        binding.recycler.adapter = adapter
        adapter.submitList(item.items)
    }

    override fun getId(): String {
        return section?.id ?: throw IllegalStateException("Section is null!")
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager? {
        return binding.recycler.layoutManager
    }
}