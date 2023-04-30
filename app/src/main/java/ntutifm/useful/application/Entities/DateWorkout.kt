package ntutifm.useful.application.Entities

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

data class DateWorkout(
var id: Int,
var title: MutableState<String> = mutableStateOf(""),
var complete: MutableState<Boolean> = mutableStateOf(false),
var dateOrder: MutableState<Int> = mutableStateOf(0),
)
val DateWorkoutList = mutableListOf(DateWorkout(0))

class DateWorkoutViewModel : ViewModel() {

    private fun getDateWorkout(): SnapshotStateList<DateWorkout> {
        return DateWorkoutList.toMutableStateList()
    }
    private val _dateWorkoutList = getDateWorkout().toMutableStateList()
    val dateWorkoutList: SnapshotStateList<DateWorkout>
        get() = _dateWorkoutList
}