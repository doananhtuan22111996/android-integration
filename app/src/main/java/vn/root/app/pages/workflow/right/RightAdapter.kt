package vn.root.app.pages.workflow.right

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.feature.app.databinding.ItemPagingBinding
import vn.root.domain.model.ItemModel

class RightAdapter :
    PagingDataAdapter<ItemModel, RightAdapter.ItemViewHolder>(
        differCallback,
    ) {

    companion object {
        val differCallback = object : ItemCallback<ItemModel>() {
            override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                // Use == because ItemModel is Data Class
                return oldItem == newItem
            }
        }
    }

    inner class ItemViewHolder(private val viewBinding: ItemPagingBinding) : ViewHolder(viewBinding.root) {
        fun bind(data: ItemModel?) {
            viewBinding.item = data
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder = ItemViewHolder(
        ItemPagingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ),
    )
}
