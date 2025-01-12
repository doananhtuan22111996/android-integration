package vn.root.app.pages.materialKit.carousel

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.carousel.MultiBrowseCarouselStrategy
import com.google.android.material.carousel.UncontainedCarouselStrategy
import vn.main.app.R
import vn.main.app.databinding.FragmentCarouselBinding

class CarouselFragment : Fragment() {

    private lateinit var viewBinding: FragmentCarouselBinding
    private lateinit var navController: NavController
    private lateinit var adapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CarouselAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentCarouselBinding.inflate(inflater, container, false)
        navController = findNavController()
        return viewBinding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.layoutHeader.toolbar.apply {
            title = getString(R.string.carousel)
            setNavigationOnClickListener { navController.popBackStack() }
            menu.clear()
        }

        val snapHelper1 = CarouselSnapHelper()
        snapHelper1.attachToRecyclerView(viewBinding.multipleBrowse)
        viewBinding.multipleBrowse.apply {
            layoutManager = CarouselLayoutManager(MultiBrowseCarouselStrategy())
            adapter = this@CarouselFragment.adapter
        }

        val snapHelper2 = CarouselSnapHelper()
        snapHelper2.attachToRecyclerView(viewBinding.hero)
        viewBinding.hero.apply {
            layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
            adapter = this@CarouselFragment.adapter
        }

        val snapHelper3 = CarouselSnapHelper()
        snapHelper3.attachToRecyclerView(viewBinding.center)
        val carousel = CarouselLayoutManager(HeroCarouselStrategy())
        carousel.carouselAlignment = CarouselLayoutManager.ALIGNMENT_CENTER
        viewBinding.center.apply {
            layoutManager = carousel
            adapter = this@CarouselFragment.adapter
        }

        val snapHelper4 = CarouselSnapHelper()
        snapHelper4.attachToRecyclerView(viewBinding.uncontained)
        viewBinding.uncontained.apply {
            layoutManager = CarouselLayoutManager(UncontainedCarouselStrategy())
            adapter = this@CarouselFragment.adapter
        }
    }
}
