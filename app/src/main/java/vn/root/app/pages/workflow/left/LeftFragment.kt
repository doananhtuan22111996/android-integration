package vn.root.app.pages.workflow.left

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.feature.app.databinding.FragmentLeftRightBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import vn.core.ui.base.BaseFragment
import vn.core.ui.base.PagingLoadStateAdapter
import vn.root.app.pages.root.RootViewModel

@AndroidEntryPoint
class LeftFragment : BaseFragment<RootViewModel, LeftViewModel, FragmentLeftRightBinding>() {

    private lateinit var adapter: LeftAdapter

    override val sharedViewModel: RootViewModel by activityViewModels()

    override val viewModel: LeftViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLeftRightBinding =
        FragmentLeftRightBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = LeftAdapter()
    }

    override fun onInit(view: View, savedInstanceState: Bundle?) {
        viewBinding.rvHome.adapter =
            adapter.withLoadStateFooter(
                PagingLoadStateAdapter(retryFunc = {
                    adapter.retry()
                }),
            )
        viewBinding.swRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.paging.collectLatest {
                adapter.submitData(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadStates ->
                Timber.d("loadStateFlow $loadStates")
                viewBinding.swRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
    }
}
