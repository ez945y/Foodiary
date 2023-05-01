package ntutifm.useful.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import ntutifm.useful.application.Entities.*
import ntutifm.useful.application.Screens.Drawer
import ntutifm.useful.application.Screens.DrawerScreens
import ntutifm.useful.application.Screens.MTopBar
import ntutifm.useful.application.ui.theme.ApplicationTheme
import ntutifm.useful.application.ui.theme.isLight
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    rememberSystemUiController().setStatusBarColor(
                        Color.Transparent, darkIcons = MaterialTheme.colorScheme.isLight())
                    val navController = rememberNavController()
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val openDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                    val context = LocalContext.current
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        gesturesEnabled = drawerState.isOpen,
                        drawerContent = {
                            Drawer(
                                onDestinationClicked = { route ->
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(route) {
                                        popUpTo = navController.graph.startDestinationId
                                        launchSingleTop = true
                                    }
                                }, context = context)
                        },

                        ) {
                        NavHost(
                            navController = navController,
                            startDestination = "Main"
                        ) {
                            composable(route = DrawerScreens.Main.route) {
                                RunApp(
                                    navController = navController,
                                    openDrawer = {
                                        openDrawer()
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RunApp(
    navController: NavController,
    openDrawer: () -> Unit,
) {
    val visible = remember { mutableStateOf(false) }
    val mode = remember { mutableStateOf(0) }
    var showAlertDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            MTopBar({ showAlertDialog = true }, mode, navController, openDrawer)
        },
        floatingActionButton = {
            if (mode.value == 0) {
                FloatingActionButton(
                    onClick = { },
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 120.dp)
                ) {
                    Icon(Icons.Filled.Add, null, modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            mode.value = 1
                            visible.value = true
                        })
                }
            }
        }
    ) { contentPadding ->
        Box(Modifier.padding(contentPadding)) {
            InitDB()
            if (mode.value == 1) {
                AddDiary(visible)
                if(!visible.value){
                DiaryDisplay(mode)
                }
                Caculator(visible)

            } else {
                Weeks()
                DiaryDisplay(mode)
            }
        }
    }
}

@Composable
fun AddDiary(visible: MutableState<Boolean>) {
    Box {
        Divider(modifier=Modifier.padding(top=30.dp,start = 100.dp, end = 20.dp), thickness = 1.dp)
        Row(modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth()
            .height(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column() {
                Text("2023")
                Text("0426")
            }
            Image(painterResource(id = R.drawable.repeat), null, Modifier.size(24.dp))
            Card() {
                Box(modifier = Modifier.size(120.dp, 30.dp), contentAlignment = Alignment.Center) {
                    Text("豬排面")
                }
            }
            Card(Modifier.clickable { visible.value = true }) {
                Box(modifier = Modifier.size(100.dp, 30.dp), contentAlignment = Alignment.Center) {
                    Text("250g")
                }
            }

        }

    }
}


@Composable
fun Weeks() {
    val mDayOfWeek = listOf("週一", "週二", "週三", "週四", "週五", "週六", "週日")
    val mDayOfMonth = listOf(23, 24, 25, 26, 27, 28, 29)
    Column() {
        LazyRow(modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth()
            .height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround) {
            items(mDayOfWeek) {
                Box {
                    Text(text = it)
                }
            }
        }
        LazyRow(modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth()
            .height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround) {
            items(mDayOfMonth) {
                Box {
                    Text(text = it.toString())
                }
            }
        }
    }

}

@Composable
fun DiaryDisplay(mode: MutableState<Int>,) {
    Card(modifier = Modifier
        .padding(top = 80.dp)
        .fillMaxSize().clickable {mode.value = 0 }) {
        Column(modifier = Modifier.padding(top = 20.dp)) {
            Row(modifier = Modifier
                .padding(start = 40.dp, end = 40.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    Icon(Icons.Filled.Add, null, modifier = Modifier
                        .size(20.dp))
                    Text("蛋白")
                }
                Row {
                    Icon(Icons.Filled.Add, null, modifier = Modifier
                        .size(20.dp))
                    Text("碳水")
                }
                Row {
                    Icon(Icons.Filled.Add, null, modifier = Modifier
                        .size(20.dp))
                    Text("熱量")
                }
                Row {
                    Icon(Icons.Filled.Add, null, modifier = Modifier
                        .size(20.dp))
                    Text("纖維")
                }
            }
            val diaryVM = DiaryViewModel()
            val diaryList = diaryVM.diaryList
            LazyColumn() {
                items(diaryList) { diaryItem ->
                    val food = selectFood(diaryItem.foodId.value, LocalContext.current)
                    Row(modifier = Modifier.padding(top = 40.dp)) {
                        Icon(Icons.Filled.Add, null, modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .size(20.dp))
                        Card() {
                            Row(Modifier
                                .padding(start = 20.dp, end = 40.dp)
                                .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("${food.protein.value * diaryItem.gram.value}g")
                                Text("${food.carbohydrates.value * diaryItem.gram.value}g")
                                Text("${food.fibre.value * diaryItem.gram.value}g")
                                Text("${food.kilocalorie.value * diaryItem.gram.value}g")
                            }
                        }
                    }
                }
            }
            Divider(modifier = Modifier.padding(top = 20.dp))
        }
    }
}

@Composable
fun Caculator(visible: MutableState<Boolean>) {
    if (visible.value) {
        val temp = remember { mutableStateOf("") }
        val number = remember { mutableStateOf("0") }
        val cFlag = remember { mutableStateOf(0) }
        Box(Modifier
            .padding(top = 130.dp)
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter) {
            Card(Modifier
                .padding(bottom = 500.dp)
                .clickable { visible.value = false }) {
                Box(modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth(),
                    contentAlignment = Alignment.Center) {
                    Text(number.value,
                        fontSize = 50.sp)
                }
            }
            Card(Modifier
                .fillMaxWidth()
                .height(400.dp)) {
                Column(Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)) {
                    Row(Modifier.padding(start = 2.dp, end = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Image(painterResource(R.drawable.cback),
                            contentDescription = null,
                            Modifier
                                .size(200.dp, 60.dp)
                                .clickable {
                                    if (number.value.isNotEmpty() && number.value != "0") {
                                        number.value =
                                            number.value
                                                .subSequence(0, number.value.length - 1)
                                                .toString()
                                        if (number.value.isEmpty()) {
                                            number.value = "0"
                                        }
                                    }
                                })
                        Image(painterResource(R.drawable.cclean),
                            contentDescription = null,
                            Modifier
                                .size(110.dp, 60.dp)
                                .clickable { number.value = "0" })
                        Image(painterResource(R.drawable.ctime),
                            contentDescription = null,
                            Modifier
                                .size(120.dp, 60.dp)
                                .clickable {
                                    cFlag.value = 4
                                    temp.value = number.value
                                    number.value = "0"
                                })
                    }
                    Row(Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Image(painterResource(R.drawable.c7),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "7"
                                    } else {
                                        number.value = "7"
                                    }
                                })
                        Image(painterResource(R.drawable.c8),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "8"
                                    } else {
                                        number.value = "8"
                                    }
                                })
                        Image(painterResource(R.drawable.c9),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "9"
                                    } else {
                                        number.value = "9"
                                    }
                                })
                        Image(painterResource(R.drawable.cdiv),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    cFlag.value = 3
                                    temp.value = number.value
                                    number.value = "0"
                                })
                    }
                    Row(Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Image(painterResource(R.drawable.c4),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "4"
                                    } else {
                                        number.value = "4"
                                    }
                                })
                        Image(painterResource(R.drawable.c5),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "5"
                                    } else {
                                        number.value = "5"
                                    }
                                })
                        Image(painterResource(R.drawable.c6),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "6"
                                    } else {
                                        number.value = "6"
                                    }
                                })
                        Image(painterResource(R.drawable.csub),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    cFlag.value = 2
                                    temp.value = number.value
                                    number.value = "0"
                                })
                    }
                    Row(Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Image(painterResource(R.drawable.c1),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "1"
                                    } else {
                                        number.value = "1"
                                    }
                                })
                        Image(painterResource(R.drawable.c2),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "2"
                                    } else {
                                        number.value = "2"
                                    }
                                })
                        Image(painterResource(R.drawable.c3),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "3"
                                    } else {
                                        number.value = "3"
                                    }
                                })

                        Image(painterResource(R.drawable.cadd),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    cFlag.value = 1
                                    temp.value = number.value
                                    number.value = "0"
                                })
                    }
                    Row(Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Image(painterResource(R.drawable.center),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable { })
                        Image(painterResource(R.drawable.c0),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "0"
                                    }
                                })
                        Image(painterResource(R.drawable.c00),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (number.value != "0") {
                                        number.value += "00"
                                    }
                                })
                        Image(painterResource(R.drawable.cequal),
                            contentDescription = null,
                            Modifier
                                .size(100.dp, 60.dp)
                                .clickable {
                                    if (cFlag.value == 1) {
                                        number.value =
                                            (number.value.toInt() + temp.value.toInt()).toString()
                                    }
                                    if (cFlag.value == 2) {
                                        number.value =
                                            (number.value.toInt() - temp.value.toInt()).toString()
                                    }
                                    if (cFlag.value == 3) {
                                        number.value =
                                            (number.value.toInt() / temp.value.toInt())
                                                .toInt()
                                                .toString()
                                    }
                                    if (cFlag.value == 4) {
                                        number.value =
                                            (number.value.toInt() * temp.value.toInt()).toString()
                                    }
                                    cFlag.value = 0
                                })
                    }
                }

            }

        }
    }
}
