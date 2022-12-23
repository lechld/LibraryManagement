package at.aau.iteractivesystems.library.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemHeadlineBinding
import at.aau.iteractivesystems.library.databinding.ItemHeadlineSmallBinding
import at.aau.iteractivesystems.library.databinding.ItemSearchBinding
import at.aau.iteractivesystems.library.databinding.ItemSectionBinding
import at.aau.iteractivesystems.library.ui.main.adapter.viewholder.HeadlineViewHolder
import at.aau.iteractivesystems.library.ui.main.adapter.viewholder.SearchViewHolder
import at.aau.iteractivesystems.library.ui.main.adapter.viewholder.SectionViewHolder
import at.aau.iteractivesystems.library.ui.main.adapter.viewholder.HeadlineSmallViewHolder

private const val SEARCH_VIEW_TYPE = 0
private const val HEADLINE_VIEW_TYPE = 1
private const val HEADLINE_SMALL_VIEW_TYPE = 2
private const val SECTION_VIEW_TYPE = 3

class ContentAdapter : ListAdapter<Content, RecyclerView.ViewHolder>(ContentDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Content.Search -> SEARCH_VIEW_TYPE
            is Content.Headline -> HEADLINE_VIEW_TYPE
            is Content.HeadlineSmall -> HEADLINE_SMALL_VIEW_TYPE
            is Content.Section -> SECTION_VIEW_TYPE
            else -> throw IllegalStateException("Invalid ViewHolder type!")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            SEARCH_VIEW_TYPE -> {
                val binding = ItemSearchBinding.inflate(inflater, parent, false)

                SearchViewHolder(binding)
            }
            HEADLINE_VIEW_TYPE -> {
                val binding = ItemHeadlineBinding.inflate(inflater, parent, false)

                HeadlineViewHolder(binding)
            }
            HEADLINE_SMALL_VIEW_TYPE -> {
                val binding = ItemHeadlineSmallBinding.inflate(inflater, parent, false)

                HeadlineSmallViewHolder(binding)
            }
            SECTION_VIEW_TYPE -> {
                val binding = ItemSectionBinding.inflate(inflater, parent, false)

                SectionViewHolder(binding)
            }
            else -> throw IllegalStateException("Invalid viewType!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is SearchViewHolder -> {
                holder.bind(item as Content.Search)
            }
            is HeadlineViewHolder -> {
                holder.bind(item as Content.Headline)
            }
            is HeadlineSmallViewHolder -> {
                holder.bind(item as Content.HeadlineSmall)
            }
            is SectionViewHolder -> {
                holder.bind(item as Content.Section)
            }
            else -> throw IllegalStateException("Can't bind type!")
        }
    }

    private class ContentDiffUtil : DiffUtil.ItemCallback<Content>() {

        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            if (oldItem == newItem) return true

            if (oldItem is Content.Search && newItem is Content.Search) {
                return true
            }
            if (oldItem is Content.Headline && newItem is Content.Headline) {
                return oldItem.text == newItem.text
            }
            if (oldItem is Content.HeadlineSmall && newItem is Content.HeadlineSmall) {
                return oldItem.text == newItem.text
            }
            if (oldItem is Content.Section && newItem is Content.Section) {
                return oldItem.id == newItem.id
            }

            return false
        }

        override fun areContentsTheSame(
            oldItem: Content,
            newItem: Content
        ): Boolean {
            return oldItem == newItem
        }
    }
}