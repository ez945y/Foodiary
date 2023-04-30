package ntutifm.useful.application.Entities

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import java.time.LocalDate

data class Personal(
    var id: Int,
    var sex: MutableState<Int> = mutableStateOf(0),
    var ages: MutableState<Int> = mutableStateOf(0),
    var height: MutableState<Int> = mutableStateOf(0),
    var weight: MutableState<Int> = mutableStateOf(0),
    var targetWeight: MutableState<Int> =  mutableStateOf(0),
    var muscles: MutableState<Int> =  mutableStateOf(0),
    var targetCarbohydrates: MutableState<Int> = mutableStateOf(0),
    var dayStart: MutableState<LocalDate> = mutableStateOf(LocalDate.now()),
    var interval: MutableState<Int> = mutableStateOf(0),
    )
val PersonalList = mutableListOf(Personal(0))

class PersonalViewModel : ViewModel() {

    private fun getPersonal(): SnapshotStateList<Personal> {
        return PersonalList.toMutableStateList()
    }
    private val _personalList = getPersonal().toMutableStateList()
    val personalList: SnapshotStateList<Personal>
        get() = _personalList
}
