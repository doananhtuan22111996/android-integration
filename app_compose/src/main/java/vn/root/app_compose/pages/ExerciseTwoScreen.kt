package vn.root.app_compose.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import vn.root.app_compose.R
import vn.root.app_compose.ui.components.AppBottomNavigationBar
import vn.root.app_compose.ui.components.AppNavigationRailBar
import vn.root.app_compose.ui.components.Container
import vn.root.app_compose.ui.components.NavElement
import vn.root.app_compose.ui.components.textField.SearchField

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun ExerciseTwoPreview() {
	ExerciseTwo(windowSizeClass = WindowSizeClass.calculateFromSize(size = DpSize(0.dp, 0.dp)))
}

@Composable
fun ExerciseTwo(windowSizeClass: WindowSizeClass, onBackPress: () -> Unit = {}) {
	println("windowSizeClass: $windowSizeClass")
	Container(
		appBarTitle = stringResource(R.string.exercise_two),
		navigationIcon = {
			IconButton(onClick = onBackPress) {
				Icon(
					imageVector = Icons.AutoMirrored.Filled.ArrowBack,
					contentDescription = stringResource(
						id = R.string.icon
					)
				)
			}
		},
		bottomBar = {
			if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
				AppBottomNavigationBar(
					items = listOf(
						NavElement(
							icon = {
								Icon(
									Icons.Filled.Home,
									contentDescription = stringResource(R.string.icon)
								)
							},
							label = {
								Text(stringResource(R.string.home))
							},
							onClick = {},
						),
						NavElement(
							icon = {
								Icon(
									Icons.Filled.AccountCircle,
									contentDescription = stringResource(R.string.icon)
								)
							},
							label = {
								Text(stringResource(R.string.account))
							},
							onClick = {},
						),
					),
				)
			}
		},
	) { innerPadding ->
		Surface(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
		) {
			if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
				ExerciseTwoContainer()
			}
			if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium) {
				Row {
					AppNavigationRailBar(
						items = listOf(
							NavElement(
								icon = {
									Icon(
										Icons.Filled.Home,
										contentDescription = stringResource(R.string.icon)
									)
								},
								label = {
									Text(stringResource(R.string.home))
								},
								onClick = {},
							),
							NavElement(
								icon = {
									Icon(
										Icons.Filled.AccountCircle,
										contentDescription = stringResource(R.string.icon)
									)
								},
								label = {
									Text(stringResource(R.string.account))
								},
								onClick = {},
							),
						),
					)
					ExerciseTwoContainer()
				}
			}
		}
	}
}

@Composable
private fun ExerciseTwoContainer() {
	var search by rememberSaveable { mutableStateOf("") }
	Column(
		modifier = Modifier
			.verticalScroll(rememberScrollState())
			.padding(16.dp)
	) {
		SearchField(value = search, onValueChange = { text ->
			search = text
		})
		Text(
			text = "Align your body - Alignment", modifier = Modifier.padding(vertical = 8.dp)
		)
		AlignYourBodyRow()
		Text(
			text = "Favorite collections - Favorite", modifier = Modifier.padding(vertical = 8.dp)
		)
		FavoriteCollectionGrid()
	}
}

@Composable
private fun AlignYourBodyRow(
	items: List<String> = listOf(
		"Body 1", "Body 2", "Body 3", "Body 4", "Body 5", "Body 6"
	)
) {
	LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
		items(items) {
			AlignYourBodyRowElement(name = it)
		}
	}
}

@Composable
private fun AlignYourBodyRowElement(name: String) {
	Card {
		Column(horizontalAlignment = Alignment.CenterHorizontally) {
			Image(
				painter = painterResource(id = R.drawable.im_3d_avatar),
				contentDescription = stringResource(
					id = R.string.image
				),
				modifier = Modifier.padding(8.dp)
			)
			Text(text = name)
			Spacer(modifier = Modifier.height(4.dp))
		}
	}
}

@Composable
private fun FavoriteCollectionGrid(
	items: List<String> = listOf(
		"Collection 1",
		"Collection 2",
		"Collection 3",
		"Collection 4",
		"Collection 5",
		"Collection 6"
	)
) {
	LazyHorizontalGrid(
		rows = GridCells.Fixed(2),
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp),
		modifier = Modifier.height(120.dp)
	) {
		items(items) {
			FavoriteCollectionGridElement(name = it)
		}
	}
}

@Composable
private fun FavoriteCollectionGridElement(name: String) {
	Card(modifier = Modifier.wrapContentHeight()) {
		Row(verticalAlignment = Alignment.CenterVertically) {
			Image(
				painter = painterResource(id = R.drawable.im_3d_avatar),
				contentDescription = stringResource(
					id = R.string.image
				),
				modifier = Modifier.padding(8.dp)
			)
			Text(text = name, modifier = Modifier.padding(8.dp))
		}
	}
}