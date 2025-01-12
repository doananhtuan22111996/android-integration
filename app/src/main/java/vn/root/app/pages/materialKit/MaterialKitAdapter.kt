package vn.root.app.pages.materialKit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import vn.main.app.databinding.ItemMaterialKitBinding

class MaterialKitAdapter(private val kits: List<MaterialKit>) : RecyclerView.Adapter<MaterialKitAdapter.KitViewHolder>() {
    inner class KitViewHolder(private val viewBinding: ItemMaterialKitBinding) : ViewHolder(viewBinding.root) {

        fun bind(kit: MaterialKit) {
            viewBinding.kit = kit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitViewHolder = KitViewHolder(
        viewBinding = ItemMaterialKitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ),
    )

    override fun getItemCount(): Int = kits.size

    override fun onBindViewHolder(holder: KitViewHolder, position: Int) {
        holder.bind(kits[position])
    }
}
