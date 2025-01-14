package vn.root.app.pages.materialKit.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.feature.app.R
import com.feature.app.databinding.ItemCarouselBinding

class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(private val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.imageView.load(
                R.drawable.im_material_kit,
                builder = {
                    transformations(RoundedCornersTransformation(28f))
                },
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder = CarouselViewHolder(
        ItemCarouselBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ),
    )

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind()
    }
}
