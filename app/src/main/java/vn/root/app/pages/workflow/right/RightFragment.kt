package vn.root.app.pages.workflow.right

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import vn.root.app.base.BaseFragment
import vn.root.app.base.PagingLoadStateAdapter
import vn.root.app.databinding.FragmentLeftRightBinding
import vn.root.app.pages.root.RootViewModel

class RightFragment : BaseFragment<RootViewModel, RightViewModel, FragmentLeftRightBinding>() {
	
	private lateinit var adapter: RightAdapter
	
	override val sharedViewModel: RootViewModel by activityViewModel()
	
	override val viewModel: RightViewModel by viewModel()
	
	override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLeftRightBinding =
		FragmentLeftRightBinding::inflate
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		adapter = RightAdapter()
	}
	
	override fun onInit(view: View, savedInstanceState: Bundle?) {
		viewBinding.rvHome.adapter =
			adapter.withLoadStateFooter(PagingLoadStateAdapter(retryFunc = {
				adapter.retry()
			}))
		viewBinding.swRefresh.setOnRefreshListener {
			adapter.refresh()
		}
	}
	
	override fun bindViewModel() {
		super.bindViewModel()
		viewLifecycleOwner.lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.paging.collectLatest {
					adapter.submitData(it)
				}
			}
		}
		
		viewLifecycleOwner.lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				adapter.loadStateFlow.collect { loadStates ->
					Timber.d("loadStateFlow $loadStates")
					viewBinding.swRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
				}
			}
		}
	}
}
