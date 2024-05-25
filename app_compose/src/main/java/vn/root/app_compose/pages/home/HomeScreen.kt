package vn.root.app_compose.pages.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import vn.root.app_compose.R
import vn.root.app_compose.ui.components.AppBottomNavigationBar
import vn.root.app_compose.ui.components.Container
import vn.root.app_compose.ui.components.NavElement

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
	HomeScreen()
}

@Composable
fun HomeScreen(onBackPress: () -> Unit = {}) {
	val viewModel: HomeViewModel = hiltViewModel()
	var selectedItem by rememberSaveable { mutableIntStateOf(0) }
	var navVisibility by rememberSaveable { mutableStateOf(true) }
	
	LaunchedEffect(viewModel.networkScrollState) {
		snapshotFlow {
			viewModel.networkScrollState.firstVisibleItemIndex
		}.collect { firstIndex ->
			navVisibility = firstIndex <= 2
		}
	}
	
	LaunchedEffect(viewModel.localScrollState) {
		snapshotFlow {
			viewModel.localScrollState.firstVisibleItemIndex
		}.collect { firstIndex ->
			navVisibility = firstIndex <= 2
		}
	}
	
	Container(
		appBarTitle = stringResource(id = R.string.home),
		navigationIcon = {
			IconButton(onClick = onBackPress) {
				Icon(
					imageVector = Icons.AutoMirrored.Filled.ArrowBack,
					contentDescription = stringResource(
						id = R.string.icon
					),
				)
			}
		},
		bottomBar = {
			AnimatedVisibility(visible = navVisibility) {
				BottomAppBar {
					AppBottomNavigationBar(
						items = listOf(
							NavElement(
								icon = {
									Icon(
										Icons.Filled.Accessibility,
										contentDescription = stringResource(R.string.icon)
									)
								},
								label = {
									Text(stringResource(R.string.accessibility))
								},
								onClick = {
									selectedItem = 0
								},
								selected = selectedItem == 0
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
								onClick = {
									selectedItem = 1
								},
								selected = selectedItem == 1
							),
						),
					)
				}
			}
		},
		floatingActionButtonPosition = FabPosition.End,
		floatingActionButton = {
			AnimatedVisibility(visible = navVisibility) {
				FloatingActionButton(onClick = { /*TODO*/ }) {
					Icon(
						imageVector = Icons.Filled.Add,
						contentDescription = stringResource(id = R.string.icon)
					)
				}
			}
		},
	) {
		Box(modifier = Modifier.padding(it)) {
			AnimatedVisibility(visible = selectedItem == 0) {
				AccessibilityScreen(viewModel)
			}
			AnimatedVisibility(visible = selectedItem == 1) {
				AccountCircleScreen(viewModel)
			}
		}
	}
}
