package vn.root.app_compose.ui.components.paging

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import vn.root.app_compose.R
import vn.root.domain.model.BaseModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun <T : BaseModel> Paging(
	lazyPagingItems: LazyPagingItems<T>,
	lazyListState: LazyListState = rememberLazyListState(),
	items: @Composable (index: Int) -> Unit = {},
	footer: @Composable (() -> Unit)? = null,
	onRetry: (() -> Unit)? = null,
) {
	val isInitialLoading =
		lazyPagingItems.itemCount == 0 && lazyPagingItems.loadState.refresh is LoadState.Loading
	val isInitialError =
		lazyPagingItems.itemCount == 0 && lazyPagingItems.loadState.refresh is LoadState.Error
	val isPagingFooter =
		lazyPagingItems.loadState.append is LoadState.Loading || lazyPagingItems.loadState.append is LoadState.Error
	
	val pullToRefreshState = rememberPullToRefreshState()
	
	if (pullToRefreshState.isRefreshing) {
		LaunchedEffect(key1 = true) {
			lazyPagingItems.refresh()
		}
	}
	
	// Initial no need to refresh
	if (lazyPagingItems.loadState.refresh is LoadState.Loading && lazyPagingItems.itemCount > 0) {
		LaunchedEffect(key1 = true) {
			pullToRefreshState.startRefresh()
		}
	} else {
		LaunchedEffect(key1 = true) {
			pullToRefreshState.endRefresh()
		}
	}
	
	Box(
		modifier = Modifier
			.fillMaxSize()
			.nestedScroll(pullToRefreshState.nestedScrollConnection)
	) {
		
		if (isInitialLoading) {
			CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			return@Box
		}
		
		if (isInitialError) {
			val message = (lazyPagingItems.loadState.refresh as LoadState.Error).error.message
				?: stringResource(
					id = R.string.unknown_error
				)
			Box(modifier = Modifier.align(Alignment.Center)) {
				PagingRetry(message = message, onRetry = onRetry ?: { lazyPagingItems.retry() })
			}
			return@Box
		}
		
		LazyColumn(
			state = lazyListState,
			modifier = Modifier.fillMaxSize(),
			verticalArrangement = Arrangement.spacedBy(8.dp),
			contentPadding = PaddingValues(16.dp)
		) {
			items(
				count = lazyPagingItems.itemCount,
				key = lazyPagingItems.itemKey { it.hashCode() }) { index ->
				Box(modifier = Modifier.animateItemPlacement()) {
					items(index)
				}
			}
			if (isPagingFooter) {
				footer ?: item {
					Box(modifier = Modifier.animateItemPlacement()) {
						PagingFooter(
							loadState = lazyPagingItems.loadState.append,
							onRetry = onRetry ?: {
								lazyPagingItems.retry()
							})
					}
				}
			}
		}
		
		PullToRefreshContainer(
			modifier = Modifier.align(Alignment.TopCenter),
			state = pullToRefreshState,
		)
	}
}