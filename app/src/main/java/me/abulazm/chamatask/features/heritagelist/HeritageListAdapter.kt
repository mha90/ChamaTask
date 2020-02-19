package me.abulazm.chamatask.features.heritagelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.abulazm.chamatask.R
import me.abulazm.chamatask.data.model.HeritageItem
import me.abulazm.chamatask.utils.load

class HeritageListAdapter(private val itemClickListener: OnHeritageItemClickListener) :
    PagedListAdapter<HeritageItem, HeritageListAdapter.HeritageItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeritageItemViewHolder {
        return HeritageItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_heritage,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeritageItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, itemClickListener) }

    }

    inner class HeritageItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView by lazy { itemView.findViewById<ImageView>(R.id.heritageImageView) }
        private val nameTextView: TextView by lazy { itemView.findViewById<TextView>(R.id.heritageNameTextView) }
        private val shortInfoTextView: TextView by lazy { itemView.findViewById<TextView>(R.id.heritageShortInfoTextView) }

        fun bind(
            item: HeritageItem,
            itemClickListener: OnHeritageItemClickListener
        ) {
            itemView.setOnClickListener { itemClickListener.onHeritageItemClick(item) }

            with(item) {
                nameTextView.text = name
                shortInfoTextView.text = shortInfo
                imageView.load(image)
            }
        }
    }

    interface OnHeritageItemClickListener {
        fun onHeritageItemClick(item: HeritageItem)
    }
}


class DiffCallback : DiffUtil.ItemCallback<HeritageItem>() {
    override fun areItemsTheSame(oldItem: HeritageItem, newItem: HeritageItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: HeritageItem, newItem: HeritageItem): Boolean {
        return oldItem == newItem
    }

}