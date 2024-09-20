package vn.root.app_compose.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.core.composex.uikit.Container
import vn.main.appCompose.R

@Preview(showBackground = true)
@Composable
fun Main(onItemClick: (index: Int) -> Unit = {}) {
	val items = listOf("Exercise One", "Exercise Two", "Exercise Three", "Workflow")
	Container(appBarTitle = "Main", navigationIcon = {
		Icon(
			imageVector = Icons.Filled.Menu, contentDescription = stringResource(id = R.string.icon)
		)
	}) { innerPadding ->
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth()
				.padding(innerPadding)
		) {
			items(items) {
				MenuItem(name = it, onClick = { onItemClick(items.indexOf(it)) })
			}
		}
	}
}

@Composable
private fun MenuItem(name: String, onClick: () -> Unit) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp, horizontal = 16.dp),
		onClick = onClick
	) {
		Row(verticalAlignment = Alignment.CenterVertically) {
			Image(
				painter = painterResource(id = R.drawable.im_3d_avatar),
				contentDescription = stringResource(R.string.image),
				modifier = Modifier.padding(8.dp)
			)
			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 16.dp),
				text = name,
				style = MaterialTheme.typography.bodyLarge.copy(
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}