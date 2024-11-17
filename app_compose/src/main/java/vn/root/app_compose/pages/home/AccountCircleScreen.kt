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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import vn.core.composex.uikit.paging.Paging
import vn.root.domain.model.ItemModel

@Preview(showBackground = true)
@Composable
fun AccountCirclePreview() {
    AccountCircleScreen(viewModel = viewModel())
}

@Composable
fun AccountCircleScreen(viewModel: HomeViewModel) {
    val paging = viewModel.localPaging.collectAsLazyPagingItems()

    Paging(
        lazyPagingItems = paging, lazyListState = viewModel.localScrollState,
        items = { index ->
            AccountItem(paging[index])
        },
    )
}

@Composable
private fun AccountItem(model: ItemModel?) {
    if (model == null) return
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = model.id.toString(), style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = model.name)
        }
    }
}