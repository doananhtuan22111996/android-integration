package vn.root.app_compose.pages.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vn.core.composex.uikit.Container
import vn.core.domain.ResultModel
import vn.main.appCompose.R

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

@Composable
fun LoginScreen(onBackPress: () -> Unit = {}, onLoginSuccess: () -> Unit = {}) {
    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.loginState.collectAsState(ResultModel.Done)
    var alertSuccessDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var alertFailureDialog by rememberSaveable {
        mutableStateOf(Pair<Boolean, String?>(false, null))
    }

    Container(appBarTitle = "Login", navigationIcon = {
        IconButton(onClick = { onBackPress() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(
                    id = R.string.icon
                )
            )
        }
    }) { innerPadding ->
        Box(
            modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
				.verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.intro),
                    contentDescription = stringResource(id = R.string.image),
                )
                Button(modifier = Modifier
					.fillMaxWidth()
					.padding(16.dp),
                    onClick = { viewModel.onLogin() }) {
                    Text(text = "Login")
                }
            }
            when (state) {
                is ResultModel.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )

                is ResultModel.Success -> alertSuccessDialog = true

                is ResultModel.AppException -> alertFailureDialog = Pair(
                    true, (state as ResultModel.AppException).message
                )

                else -> {
                }
            }
            if (alertSuccessDialog) {
                LoginSuccessDialog(onDismissRequest = {
                    alertSuccessDialog = false
                }, onConfirmRequest = {
                    alertSuccessDialog = false
                    onLoginSuccess()
                })
            }
            if (alertFailureDialog.first) {
                LoginFailureDialog(message = alertFailureDialog.second ?: "Some things wrong",
                    onDismissRequest = {
                        alertFailureDialog = Pair(false, null)
                    })
            }
        }
    }
}

@Composable
private fun LoginSuccessDialog(onDismissRequest: () -> Unit, onConfirmRequest: () -> Unit = {}) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = stringResource(id = R.string.icon)
            )
        },
        title = {
            Text(text = "Login Success")
        },
        text = {
            Text(
                text = "You have successfully logged in",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirmRequest) {
                Text("Confirm")
            }
        },
    )
}

@Composable
private fun LoginFailureDialog(message: String, onDismissRequest: () -> Unit) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Filled.Error,
                contentDescription = stringResource(id = R.string.icon)
            )
        },
        title = {
            Text(text = "Login Failure")
        },
        text = {
            Text(
                text = message
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Confirm")
            }
        },
    )
}