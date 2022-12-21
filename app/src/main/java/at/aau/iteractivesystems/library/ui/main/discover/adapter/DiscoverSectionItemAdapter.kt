package at.aau.iteractivesystems.library.ui.main.discover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemDiscoverSectionItemBinding

private const val SECTION_ITEM_VIEW_TYPE = 0

class DiscoverSectionAdapter : ListAdapter<DiscoverElement.Section.Item, RecyclerView.ViewHolder>(
    DiscoverSectionDiffUtil()
) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DiscoverElement.Section.Item -> SECTION_ITEM_VIEW_TYPE
            else -> throw IllegalStateException("Invalid ViewHolder type!")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            SECTION_ITEM_VIEW_TYPE -> {
                val binding = ItemDiscoverSectionItemBinding.inflate(inflater, parent, false)

                DiscoverSectionItemViewHolder(binding)
            }
            else -> throw IllegalStateException("Invalid viewType!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is DiscoverSectionItemViewHolder -> {
                holder.bind(item as DiscoverElement.Section.Item)
            }
            else -> throw IllegalStateException("Can't bind type!")
        }
    }

    private class DiscoverSectionDiffUtil : DiffUtil.ItemCallback<DiscoverElement.Section.Item>() {

        override fun areItemsTheSame(
            oldItem: DiscoverElement.Section.Item,
            newItem: DiscoverElement.Section.Item
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DiscoverElement.Section.Item,
            newItem: DiscoverElement.Section.Item
        ): Boolean {
            return oldItem == newItem
        }
    }
}