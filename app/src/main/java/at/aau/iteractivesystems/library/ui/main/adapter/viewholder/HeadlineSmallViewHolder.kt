package at.aau.iteractivesystems.library.ui.main.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemHeadlineSmallBinding
import at.aau.iteractivesystems.library.ui.main.adapter.Content

class HeadlineSmallViewHolder(
    private val binding: ItemHeadlineSmallBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Content.HeadlineSmall) {
        binding.textView.text = item.text.asString(binding.root.context)
    }
}