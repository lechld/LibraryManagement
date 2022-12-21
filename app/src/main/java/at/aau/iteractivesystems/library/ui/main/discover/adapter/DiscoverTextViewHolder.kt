package at.aau.iteractivesystems.library.ui.main.discover.adapter

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemDiscoverTextBinding

class DiscoverTextViewHolder(
    private val binding: ItemDiscoverTextBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DiscoverElement.Text) {
        binding.root.text = binding.root.context.getString(item.stringRes)
    }
}