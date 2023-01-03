package at.aau.iteractivesystems.library.ui.adapter.viewholder.section

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemSectionSmallBinding
import at.aau.iteractivesystems.library.ui.adapter.Content
import com.bumptech.glide.Glide

class SmallViewHolder(
    private val binding: ItemSectionSmallBinding,
    private val onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun bind(item: Content.Section.Item) {
        Glide.with(binding.root.context)
            .load(item.imageUrl)
            .into(binding.image)

        binding.title.text = item.title
    }
}