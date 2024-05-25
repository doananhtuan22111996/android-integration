package vn.root.app_compose.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import vn.root.app_compose.ui.components.paging.Paging
import vn.root.domain.model.ItemModel

@Composable
fun AccessibilityScreen(viewModel: HomeViewModel) {
	val paging = viewModel.networkPaging.collectAsLazyPagingItems()
	
	Paging(
		lazyListState = viewModel.networkScrollState,
		lazyPagingItems = paging,
		items = { index -> AccessibilityItem(model = paging[index]) },
	)
}

@Composable
private fun AccessibilityItem(model: ItemModel?) {
	if (model == null) return
	Card(
		modifier = Modifier.fillMaxWidth()
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp)
		) {
			Text(
				text = model.id.toString(),
				style = MaterialTheme.typography.titleMedium,
			)
			Spacer(modifier = Modifier.height(8.dp))
			Text(text = model.name)
		}
	}
}