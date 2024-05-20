package vn.root.app_compose.ui.components.textField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import vn.root.app_compose.R

@Composable
fun SearchField(
	value: String,
	onValueChange: (String) -> Unit,
	enabled: Boolean = true,
) {
	OutlinedTextField(
		value = value,
		onValueChange = onValueChange,
		modifier = Modifier.fillMaxWidth(),
		enabled = enabled,
		isError = false,
		leadingIcon = {
			Icon(
				imageVector = Icons.Filled.Search,
				contentDescription = stringResource(R.string.search_field)
			)
		},
		placeholder = {
			Text(text = stringResource(R.string.search))
		},
		keyboardOptions = KeyboardOptions(
			keyboardType = KeyboardType.Text, autoCorrect = true, imeAction = ImeAction.Search
		),
		singleLine = true,
		maxLines = 1,
	)
}

@Preview(showBackground = true)
@Composable
private fun SearchFieldPreview() {
	SearchField(value = "", onValueChange = {})
}