package vn.root.app_compose.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import vn.root.app_compose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
	title: String,
	navigationIcon: @Composable () -> Unit = {},
	actions: @Composable RowScope.() -> Unit = {},
	scrollBehavior: TopAppBarScrollBehavior? = null
) {
	MediumTopAppBar(
		title = {
			Text(
				title,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				style = MaterialTheme.typography.titleLarge.copy(
					fontWeight = FontWeight.SemiBold
				)
			)
		},
		navigationIcon = navigationIcon, actions = actions, scrollBehavior = scrollBehavior,
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun AppBarPreview() {
	AppBar(
		title = "Title",
		navigationIcon = {
			IconButton(onClick = { /*TODO*/ }) {
				Icon(
					imageVector = Icons.AutoMirrored.Filled.ArrowBack,
					contentDescription = stringResource(
						id = R.string.icon
					)
				)
			}
		},
		actions = {
			IconButton(onClick = { /*TODO*/ }) {
				Icon(
					imageVector = Icons.Filled.Menu, contentDescription = stringResource(
						id = R.string.icon
					)
				)
			}
		},
	)
}