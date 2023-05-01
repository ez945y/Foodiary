package ntutifm.useful.application.Entities

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

data class Food(
    var id: Int,
    var name: MutableState<String> = mutableStateOf(""),
    var type: MutableState<Int> = mutableStateOf(0),
    var protein: MutableState<Float> = mutableStateOf(0f),
    var carbohydrates: MutableState<Float> = mutableStateOf(0f),
    var fibre: MutableState<Float> =  mutableStateOf(0f),
    var kilocalorie: MutableState<Float> =  mutableStateOf(0f),
    var defaultGram: MutableState<Int> = mutableStateOf(0),

)
val FoodList = mutableListOf(Food(0))

class FoodViewModel : ViewModel() {

    private fun getFood(): SnapshotStateList<Food> {
        return FoodList.toMutableStateList()
    }
    private val _foodList = getFood().toMutableStateList()
    val foodList: SnapshotStateList<Food>
        get() = _foodList
}