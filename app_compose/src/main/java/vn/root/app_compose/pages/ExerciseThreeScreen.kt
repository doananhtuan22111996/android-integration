package vn.root.app_compose.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import vn.root.app_compose.R
import vn.root.app_compose.ui.components.Container

data class Task(val id: Int, val name: String)

class ExerciseThreeViewModel : ViewModel() {
	private val _tasks = listOf(
		Task(1, "Task 1"),
		Task(2, "Task 2"),
		Task(3, "Task 3"),
		Task(4, "Task 4"),
		Task(5, "Task 5"),
		Task(6, "Task 6"),
		Task(7, "Task 7"),
		Task(8, "Task 8"),
		Task(9, "Task 9"),
		Task(10, "Task 10"),
	).toMutableStateList()
	val tasks: List<Task>
		get() = _tasks
	
	fun remoteTask(task: Task) {
		_tasks.remove(task)
	}
}

@Preview(showBackground = true)
@Composable
private fun ExerciseThreePreview() {
	ExerciseThree(onBackPress = {})
}

@Composable
fun ExerciseThree(onBackPress: () -> Unit, viewModel: ExerciseThreeViewModel = viewModel()) {
	Container(appBarTitle = "Exercise 3", navigationIcon = {
		IconButton(onClick = onBackPress) {
			Icon(
				imageVector = Icons.AutoMirrored.Filled.ArrowBack,
				contentDescription = stringResource(
					id = R.string.icon
				)
			)
		}
	}) {
		Box(modifier = Modifier.padding(it)) {
			TaskList(tasks = viewModel.tasks, onClick = { task ->
				viewModel.remoteTask(task)
			})
		}
	}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TaskList(tasks: List<Task>, onClick: (Task) -> Unit) {
	LazyColumn(modifier = Modifier.fillMaxSize()) {
		items(items = tasks, key = { it.id }) {
			Box(
				modifier = Modifier.animateItemPlacement()
			) {
				TaskItem(task = it, onClick = {
					onClick(it)
				})
			}
		}
	}
}

@Composable
private fun TaskItem(task: Task, onClick: () -> Unit = {}) {
	Card(modifier = Modifier
		.fillMaxWidth()
		.padding(8.dp)
		.clickable {
			onClick()
		}) {
		Column(modifier = Modifier.padding(8.dp)) {
			Text(text = "${task.id}")
			Text(
				text = task.name,
				style = MaterialTheme.typography.bodyLarge,
				modifier = Modifier.padding(4.dp)
			)
		}
	}
}