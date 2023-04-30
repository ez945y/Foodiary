package ntutifm.useful.application.Entities

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

data class DayWorkout(
    var id: Int,
    var name: MutableState<String> = mutableStateOf(""),
    var dateId:  MutableState<Int> = mutableStateOf(0),
    var weight: MutableState<Int> = mutableStateOf(0),
    var number: MutableState<Int> = mutableStateOf(0),
    var setNumber: MutableState<Int> = mutableStateOf(0),
    var dayOrder: MutableState<Int> = mutableStateOf(0),
)
val DayWorkoutList = mutableListOf(DayWorkout(0))

class DayWorkoutViewModel : ViewModel() {
    private fun getDayWorkout(): SnapshotStateList<DayWorkout> {
        return DayWorkoutList.toMutableStateList()
    }
    private val _dayWorkoutList = getDayWorkout().toMutableStateList()
    val dayWorkoutList: SnapshotStateList<DayWorkout>
        get() = _dayWorkoutList
}