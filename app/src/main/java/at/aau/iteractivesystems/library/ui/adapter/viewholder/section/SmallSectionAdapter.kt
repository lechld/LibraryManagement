package at.aau.iteractivesystems.library.ui.adapter.viewholder.section

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemSectionSmallBinding
import at.aau.iteractivesystems.library.ui.adapter.Content

private const val SMALL_VIEW_TYPE = 0

class SmallSectionAdapter(
    private val onClick: (Content.Section.Item) -> Unit
) : ListAdapter<Content.Section.Item, RecyclerView.ViewHolder>(SectionItemDiffUtl()) {

    override fun getItemViewType(position: Int): Int {
        return SMALL_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSectionSmallBinding.inflate(inflater, parent, false)

        return SmallViewHolder(binding) { position ->
            onClick(getItem(position))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) as Content.Section.Item

        if (holder is SmallViewHolder) {
            holder.bind(item)
        } else throw IllegalStateException("Invalid ViewHolder!")
    }
}