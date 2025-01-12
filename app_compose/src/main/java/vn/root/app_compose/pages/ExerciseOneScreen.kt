package vn.root.app_compose.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.core.composex.uikit.Container
import vn.main.appCompose.R

@Preview(showBackground = true)
@Composable
fun ExerciseOne(onBackPress: () -> Unit = {}) {
    Container(appBarTitle = stringResource(R.string.exercise_one), navigationIcon = {
        IconButton(onClick = onBackPress) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Localized description",
            )
        }
    }, actions = {
        IconButton(onClick = { /* do something */ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_24),
                contentDescription = "Localized description",
            )
        }
    }) { innerPadding ->
        GreetingScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}

@Composable
private fun GreetingScreen(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" },
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(items = names) { name ->
            Greeting(
                name = name,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow,
                ),
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Hello $name!")
                IconButton(onClick = {
                    expanded = !expanded
                }) {
                    AnimatedContent(
                        targetState = if (expanded) {
                            painterResource(R.drawable.baseline_expand_more_24)
                        } else {
                            painterResource(
                                id = R.drawable.baseline_expand_less_24,
                            )
                        },
                        transitionSpec = {
                            fadeIn(animationSpec = tween(800)) togetherWith fadeOut(
                                animationSpec = tween(800),
                            )
                        },
                        label = "AnimatedContent",
                    ) { targetState ->
                        Icon(
                            painter = targetState,
                            contentDescription = stringResource(R.string.icon),
                        )
                    }
                }
            }
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " + "padding theme elit, sed do bouncy. ").repeat(
                        4,
                    ),
                    modifier = Modifier.padding(vertical = 8.dp),
                )
            }
        }
    }
}
