package at.aau.iteractivesystems.library.ui.main.discover.adapter

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.databinding.ItemDiscoverSectionItemBinding

class DiscoverSectionItemViewHolder(
    private val binding: ItemDiscoverSectionItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DiscoverElement.Section.Item) {
        // TODO: Load real images with picasso or glide
        binding.image.background = AppCompatResources.getDrawable(
            binding.root.context, R.color.md_theme_dark_primary
        )
        binding.title.text = item.title
    }
}