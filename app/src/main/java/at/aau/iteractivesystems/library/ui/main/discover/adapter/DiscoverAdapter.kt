package at.aau.iteractivesystems.library.ui.main.discover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemDiscoverSectionBinding
import at.aau.iteractivesystems.library.databinding.ItemDiscoverTextBinding

private const val SECTION_VIEW_TYPE = 0
private const val TEXT_VIEW_TYPE = 1

class DiscoverAdapter : ListAdapter<DiscoverElement, RecyclerView.ViewHolder>(DiscoverDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DiscoverElement.Section -> SECTION_VIEW_TYPE
            is DiscoverElement.Text -> TEXT_VIEW_TYPE
            else -> throw IllegalStateException("Invalid ViewHolder type!")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            SECTION_VIEW_TYPE -> {
                val binding = ItemDiscoverSectionBinding.inflate(inflater, parent, false)

                DiscoverSectionViewHolder(binding)
            }
            TEXT_VIEW_TYPE -> {
                val binding = ItemDiscoverTextBinding.inflate(inflater, parent, false)

                DiscoverTextViewHolder(binding)
            }
            else -> throw IllegalStateException("Invalid viewType!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is DiscoverSectionViewHolder -> {
                holder.bind(item as DiscoverElement.Section)
            }
            is DiscoverTextViewHolder -> {
                holder.bind(item as DiscoverElement.Text)
            }
            else -> throw IllegalStateException("Can't bind type!")
        }
    }

    private class DiscoverDiffUtil : DiffUtil.ItemCallback<DiscoverElement>() {

        override fun areItemsTheSame(oldItem: DiscoverElement, newItem: DiscoverElement): Boolean {
            if (oldItem == newItem) return true

            if (oldItem is DiscoverElement.Section && newItem is DiscoverElement.Section) {
                return oldItem.id == newItem.id
            }

            return false
        }

        override fun areContentsTheSame(
            oldItem: DiscoverElement,
            newItem: DiscoverElement
        ): Boolean {
            return oldItem == newItem
        }

    }
}