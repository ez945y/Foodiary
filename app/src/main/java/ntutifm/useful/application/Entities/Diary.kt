package ntutifm.useful.application.Entities


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import java.time.LocalDate

data class Diary (
    var id: Int,
    var date: MutableState<LocalDate> = mutableStateOf(LocalDate.now()),
    var foodId: MutableState<Int> = mutableStateOf(0),
    var gram: MutableState<Int> = mutableStateOf(0),
    )

val DiaryList = mutableListOf(Diary(0))

class DiaryViewModel : ViewModel() {

    private fun getDiary(): SnapshotStateList<Diary> {
        return DiaryList.toMutableStateList()
    }
    private val _diaryList = getDiary().toMutableStateList()
    val diaryList: SnapshotStateList<Diary>
        get() = _diaryList
}