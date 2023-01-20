package at.aau.iteractivesystems.library.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemDetailHeaderBinding
import at.aau.iteractivesystems.library.databinding.ItemHeadlineBinding
import at.aau.iteractivesystems.library.databinding.ItemHeadlineSmallBinding
import at.aau.iteractivesystems.library.databinding.ItemHomeBaselineBinding
import at.aau.iteractivesystems.library.databinding.ItemSearchResultBinding
import at.aau.iteractivesystems.library.databinding.ItemSectionBinding
import at.aau.iteractivesystems.library.ui.adapter.staterestoration.NestedRecyclerViewStateRecoverAdapter
import at.aau.iteractivesystems.library.ui.adapter.viewholder.*
import at.aau.iteractivesystems.library.ui.adapter.viewholder.HomeViewHolder

private const val HEADLINE_VIEW_TYPE = 0
private const val HEADLINE_SMALL_VIEW_TYPE = 1
private const val SECTION_VIEW_TYPE = 2
private const val SEARCH_ITEM_VIEW_TYPE = 3
private const val DETAIL_VIEW_TYPE = 4
private const val HOME_VIEW_TYPE = 5

class ContentAdapter(
    private val onItemClick: ((Content) -> Unit)? = null,
) : NestedRecyclerViewStateRecoverAdapter<Content, RecyclerView.ViewHolder>(ContentDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Content.Headline -> HEADLINE_VIEW_TYPE
            is Content.HeadlineSmall -> HEADLINE_SMALL_VIEW_TYPE
            is Content.Section -> SECTION_VIEW_TYPE
            is Content.SearchResult -> SEARCH_ITEM_VIEW_TYPE
            is Content.Detail -> DETAIL_VIEW_TYPE
            is Content.Home -> HOME_VIEW_TYPE
            else -> throw IllegalStateException("Invalid ViewHolder type!")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
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

                SectionViewHolder(binding, onItemClick)
            }
            SEARCH_ITEM_VIEW_TYPE -> {
                val binding = ItemSearchResultBinding.inflate(inflater, parent, false)

                SearchResultViewHolder(binding) { position ->
                    onItemClick?.invoke(getItem(position))
                }
            }
            DETAIL_VIEW_TYPE -> {
                val binding = ItemDetailHeaderBinding.inflate(inflater, parent, false)

                DetailViewHolder(binding)
            }
            HOME_VIEW_TYPE -> {
                val binding = ItemHomeBaselineBinding.inflate(inflater, parent, false)

                HomeViewHolder(binding)
            }
            else -> throw IllegalStateException("Invalid viewType!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is HeadlineViewHolder -> {
                holder.bind(item as Content.Headline)
            }
            is HeadlineSmallViewHolder -> {
                holder.bind(item as Content.HeadlineSmall)
            }
            is SectionViewHolder -> {
                holder.bind(item as Content.Section)
            }
            is SearchResultViewHolder -> {
                holder.bind(item as Content.SearchResult)
            }
            is DetailViewHolder -> {
                holder.bind(item as Content.Detail)
            }
            is HomeViewHolder -> {
                holder.bind(item as Content.Home)
            }
            else -> throw IllegalStateException("Can't bind type!")
        }

        super.onBindViewHolder(holder, position)
    }

    private class ContentDiffUtil : DiffUtil.ItemCallback<Content>() {

        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            if (oldItem == newItem) return true

            if (oldItem is Content.Headline && newItem is Content.Headline) {
                return oldItem.text == newItem.text
            }
            if (oldItem is Content.HeadlineSmall && newItem is Content.HeadlineSmall) {
                return oldItem.text == newItem.text
            }
            if (oldItem is Content.Section && newItem is Content.Section) {
                return oldItem.id == newItem.id
            }
            if (oldItem is Content.SearchResult && newItem is Content.SearchResult) {
                return oldItem.id == newItem.id
            }
            if (oldItem is Content.Detail && newItem is Content.Detail){
                return oldItem.id == newItem.id
            }
            if (oldItem is Content.Home && newItem is Content.Home){
                return true
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