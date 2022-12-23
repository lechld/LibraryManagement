package at.aau.iteractivesystems.library.ui.main.adapter.viewholder.section

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.R
import at.aau.iteractivesystems.library.databinding.ItemSectionSmallBinding
import at.aau.iteractivesystems.library.ui.main.adapter.Content

class SmallViewHolder(
    private val binding: ItemSectionSmallBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Content.Section.Item) {
        binding.image.background = AppCompatResources.getDrawable(
            binding.root.context, R.color.md_theme_dark_primary
        )
        binding.title.text = item.title
    }
}