package at.aau.iteractivesystems.library.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import at.aau.iteractivesystems.library.databinding.ItemDetailHeaderBinding
import at.aau.iteractivesystems.library.ui.adapter.Content
import com.bumptech.glide.Glide

class DetailViewHolder(
    private val binding: ItemDetailHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(Detail : Content.Detail){
        Glide.with(binding.root.context)
            .load(Detail.imageUrl)
            .into(binding.image)
    }
}