package ntutifm.useful.application.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ntutifm.useful.application.Entities.formatter
import ntutifm.useful.application.R
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MTopBar(
    onSearchBarClick: () -> Unit,
    mode: MutableState<Int>,
    navController: NavController,
    onButtonClicked: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { Text(LocalDate.now().format(formatter)) },
        navigationIcon = {
            if (mode.value == 0) {
                Icon(Icons.Filled.Menu,
                    null,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .size(20.dp))
            }else{
                Icon(Icons.Filled.Close,
                    null,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .size(20.dp).clickable { mode.value=0 })
            }
        },
        actions = {
            if (mode.value == 0) {
                Row {
                    Icon(Icons.Filled.Search,
                        null,
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .size(20.dp)
                            .clickable { mode.value = 0 })
                }
            } else {
                Icon(Icons.Filled.Check,
                    null,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .size(20.dp)
                        .clickable {mode.value = 0})
            }
        }, )
}

@Composable
fun MBottomBar(onAddScheduleClick: () -> Unit) {
    BottomAppBar {
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Filled.Edit, null)
        }
        IconButton(onClick = { onAddScheduleClick() }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Filled.Add, null)
        }
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Filled.Share, null)
        }
    }
    Divider(
        color = MaterialTheme.colorScheme.secondary,
        thickness = 1.dp,
    )
}
