package vn.root.app_compose.ui.components.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import vn.root.app_compose.R

@Preview(showBackground = true)
@Composable
fun PagingFooterPreview() {
	PagingFooter(LoadState.Error(Exception("unknown_error")))
}

@Composable
fun PagingFooter(loadState: LoadState, onRetry: () -> Unit = {}) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp)
	) {
		if (loadState is LoadState.Loading) {
			PagingLoadMore(modifier = Modifier.align(Alignment.Center))
		}
		if (loadState is LoadState.Error) {
			val message = loadState.error.message ?: stringResource(R.string.unknown_error)
			PagingRetry(message = message, onRetry)
		}
	}
}

@Composable
private fun PagingLoadMore(modifier: Modifier = Modifier) {
	CircularProgressIndicator(modifier = modifier)
}

@Composable
fun PagingRetry(message: String, onRetry: () -> Unit = {}) {
	Column(
		modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = message,
			style = MaterialTheme.typography.bodyLarge,
		)
		Spacer(modifier = Modifier.height(4.dp))
		OutlinedButton(onClick = onRetry) {
			Text(text = stringResource(R.string.retry))
		}
	}
}