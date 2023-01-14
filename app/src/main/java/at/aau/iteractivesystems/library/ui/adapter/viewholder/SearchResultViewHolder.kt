package at.aau.iteractivesystems.library.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemSearchResultBinding
import at.aau.iteractivesystems.library.ui.adapter.Content
import com.bumptech.glide.Glide

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding,
    private val onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun bind(item: Content.SearchResult) {
        binding.author.text = item.author
        binding.title.text = item.title
        binding.publicationYear.text = item.publicationYear

        Glide.with(binding.root.context)
            .load(item.imageUrl)
            .into(binding.image)
    }
}