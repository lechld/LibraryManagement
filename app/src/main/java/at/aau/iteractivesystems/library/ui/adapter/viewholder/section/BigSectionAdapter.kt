package at.aau.iteractivesystems.library.ui.adapter.viewholder.section

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemSectionBigBinding
import at.aau.iteractivesystems.library.ui.adapter.Content

private const val BIG_VIEW_TYPE = 0

class BigSectionAdapter(
    private val onClick: (Content.Section.Item) -> Unit,
) : ListAdapter<Content.Section.Item, RecyclerView.ViewHolder>(SectionItemDiffUtl()) {

    override fun getItemViewType(position: Int): Int {
        return BIG_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSectionBigBinding.inflate(inflater, parent, false)

        return BigViewHolder(binding) { position ->
            onClick(getItem(position))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) as Content.Section.Item

        if (holder is BigViewHolder) {
            holder.bind(item)
        } else throw IllegalStateException("Invalid ViewHolder!")
    }

}