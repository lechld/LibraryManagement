package at.aau.iteractivesystems.library.ui.main.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemHeadlineBinding
import at.aau.iteractivesystems.library.ui.main.adapter.Content

class HeadlineViewHolder(
    private val binding: ItemHeadlineBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Content.Headline) {
        binding.textView.text = item.text.asString(binding.root.context)
    }
}