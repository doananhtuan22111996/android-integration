package vn.root.app.pages.workflow.right

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import vn.core.ui.base.BaseFragment
import vn.core.ui.base.PagingLoadStateAdapter
import vn.main.app.databinding.FragmentLeftRightBinding
import vn.root.app.pages.root.RootViewModel

@AndroidEntryPoint
class RightFragment : BaseFragment<RootViewModel, RightViewModel, FragmentLeftRightBinding>() {

    private lateinit var adapter: RightAdapter

    override val sharedViewModel: RootViewModel by activityViewModels()

    override val viewModel: RightViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLeftRightBinding =
        FragmentLeftRightBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RightAdapter()
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
