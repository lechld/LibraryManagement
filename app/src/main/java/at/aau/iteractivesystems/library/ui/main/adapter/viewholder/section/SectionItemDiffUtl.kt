package at.aau.iteractivesystems.library.ui.main.adapter.viewholder.section

import androidx.recyclerview.widget.DiffUtil
import at.aau.iteractivesystems.library.ui.main.adapter.Content

class SectionItemDiffUtl : DiffUtil.ItemCallback<Content.Section.Item>() {

    override fun areItemsTheSame(
        oldItem: Content.Section.Item,
        newItem: Content.Section.Item
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Content.Section.Item,
        newItem: Content.Section.Item
    ): Boolean {
        return oldItem == newItem
    }
}