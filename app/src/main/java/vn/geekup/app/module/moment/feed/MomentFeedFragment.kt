package vn.geekup.app.module.moment.feed

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import timber.log.Timber
import vn.geekup.app.R
import vn.geekup.app.base.BaseFragment
import vn.geekup.app.databinding.FragmentMomentFeedBinding
import vn.geekup.app.module.main.MainFragment
import vn.geekup.app.module.moment.MomentViewModel
import vn.geekup.app.utils.*
import androidx.recyclerview.widget.SimpleItemAnimator
import dagger.hilt.android.AndroidEntryPoint
import vn.geekup.app.domain.model.general.ResultModel

@AndroidEntryPoint
class MomentFeedFragment : BaseFragment<MomentViewModel, FragmentMomentFeedBinding>() {

    private lateinit var adapter: MomentFeedsAdapter
    private lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener
    private var dateFilter: String = ""

    override val viewModel: MomentViewModel by activityViewModels()

    override fun initViewModelByActivityLifecycle(): Boolean = true

    override fun provideViewBinding(parent: ViewGroup): FragmentMomentFeedBinding {
        return FragmentMomentFeedBinding.inflate(layoutInflater, parent, true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFlowMomentFeeds(date = dateFilter)
        initAdapter()
    }

    override fun onInitLayout(view: View, savedInstanceState: Bundle?) {
        baseActivity.setAppColorStatusBar(R.color.color_white)
        (parentFragment?.parentFragment as? MainFragment)?.bottomNavigationState(true)
        initMomentHeader()
        initRecyclerView()
        initRefreshLayout()
    }

    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.moments.observe(this) {
            fragmentBinding.isMomentEmpty = it.isEmpty()
            adapter.addAllItemsWithDiffUtils(it)
            fragmentBinding.swMoments.isRefreshing = false
        }

        viewModel.pagingState.observe(this) {
            Timber.d("Loadmore: $it")
            adapter.setNetworkState(it)
        }
    }

    override fun handleServerErrorState(serverErrorException: ResultModel.ServerErrorException?) {
        super.handleServerErrorState(serverErrorException)
        fragmentBinding.isMomentEmpty = adapter.itemCount == 0
        fragmentBinding.swMoments.isRefreshing = false
    }

    private fun initMomentHeader() {
        fragmentBinding.layoutMomentHeaderBar.day = getCurrentDayName()
    }

    private fun initAdapter() {
        adapter = MomentFeedsAdapter(
            null,
            null,
            this::onClickLinkListener
        )
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        endlessRecyclerViewScrollListener =
            object : EndlessRecyclerViewScrollListener(layoutManager) {

                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    viewModel.getFlowMomentFeeds(date = dateFilter)
                }
            }
        fragmentBinding.rvMoments.layoutManager = layoutManager
        fragmentBinding.rvMoments.adapter = adapter
        (fragmentBinding.rvMoments.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
            false
        fragmentBinding.rvMoments.addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    private fun initRefreshLayout() {
        fragmentBinding.swMoments.setOnRefreshListener {
            viewModel.getFlowMomentFeeds(date = dateFilter, isReload = true)
        }
    }

    private fun onClickLinkListener(url: String) {
        baseActivity.openBrowserApp(url)
    }

}