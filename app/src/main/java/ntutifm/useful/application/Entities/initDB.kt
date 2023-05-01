package ntutifm.useful.application.Entities

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate

@SuppressLint("UnrememberedMutableState")
@Composable
fun InitDB(){
    dbRestart(LocalContext.current)
    addDiary(
        Diary(1,
            mutableStateOf(LocalDate.now()),
            mutableStateOf(1),
            mutableStateOf(100)
        ), LocalContext.current)
    addDiary(
        Diary(2,
            mutableStateOf(LocalDate.now()),
            mutableStateOf(3),
            mutableStateOf(100)
        ), LocalContext.current)
    addDiary(
        Diary(3,
            mutableStateOf(LocalDate.now()),
            mutableStateOf(3),
            mutableStateOf(100)
        ), LocalContext.current)
    addDiary(
        Diary(4,
            mutableStateOf(LocalDate.now()),
            mutableStateOf(4),
            mutableStateOf(100)
        ), LocalContext.current)
    addFood(
        Food(1,
            mutableStateOf("Banana"),
            mutableStateOf(0),
            mutableStateOf(0.1f),
            mutableStateOf(0.5f),
            mutableStateOf(0f),
            mutableStateOf(5f),
            mutableStateOf(100),
        ), LocalContext.current)
    selectDiary(LocalDate.now(), LocalContext.current)
    addFood(
        Food(2,
            mutableStateOf("Beef"),
            mutableStateOf(0),
            mutableStateOf(0.5f),
            mutableStateOf(0.3f),
            mutableStateOf(0f),
            mutableStateOf(2f),
            mutableStateOf(100),
        ), LocalContext.current)
    selectDiary(LocalDate.now(), LocalContext.current)
    addFood(
        Food(3,
            mutableStateOf("Vegetable"),
            mutableStateOf(0),
            mutableStateOf(0f),
            mutableStateOf(0f),
            mutableStateOf(0.5f),
            mutableStateOf(1f),
            mutableStateOf(50),
        ), LocalContext.current)
    selectDiary(LocalDate.now(), LocalContext.current)
    addFood(
        Food(4,
            mutableStateOf("Rise"),
            mutableStateOf(0),
            mutableStateOf(0f),
            mutableStateOf(1f),
            mutableStateOf(0f),
            mutableStateOf(1f),
            mutableStateOf(100),
        ), LocalContext.current)
    selectDiary(LocalDate.now(), LocalContext.current)
}