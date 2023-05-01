package ntutifm.useful.application.Entities

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Debug
import android.provider.BaseColumns
import android.util.Log
import java.time.LocalDate
import java.time.ZoneId


object FeedReaderContract {
    // Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "personal"
        const val COLUMN_NAME_sex= "sex"
        const val COLUMN_NAME_ages = "ages"
        const val COLUMN_NAME_height = "height"
        const val COLUMN_NAME_weight = "weight"
        const val COLUMN_NAME_targetWeight= "targetWeight"
        const val COLUMN_NAME_muscles= "muscles"
        const val COLUMN_NAME_targetCarbohydrates = "targetCarbohydrates"
        const val COLUMN_NAME_dayStart = "dayStart"
        const val COLUMN_NAME_interval = "interval"
    }

    object FeedEntry2 : BaseColumns {
        const val TABLE_NAME = "foods"
        const val COLUMN_NAME_name = "name"
        const val COLUMN_NAME_type = "type"
        const val COLUMN_NAME_protein = "protein"
        const val COLUMN_NAME_carbohydrates = "carbohydrates"
        const val COLUMN_NAME_fibre = "fibre"
        const val COLUMN_NAME_kilocalorie = "kilocalorie"
        const val COLUMN_NAME_defaultGram = "defaultGram"
    }

    object FeedEntry3 : BaseColumns {
        const val TABLE_NAME = "diary"
        const val COLUMN_NAME_date = "date"
        const val COLUMN_NAME_foodId = "foodId"
        const val COLUMN_NAME_gram = "gram"
    }
    object FeedEntry4 : BaseColumns {
        const val TABLE_NAME = "dateWorkout"
        const val COLUMN_NAME_title = "title"
        const val COLUMN_NAME_complete = "complete"
        const val COLUMN_NAME_dateOrder = "dateOrder"
    }

    object FeedEntry5 : BaseColumns {
        const val TABLE_NAME = "workout"
        const val COLUMN_NAME_name = "name"
        const val COLUMN_NAME_dayId = "dateId"
        const val COLUMN_NAME_weight = "weight"
        const val COLUMN_NAME_number = "number"
        const val COLUMN_NAME_setNumber = "setNumber"
        const val COLUMN_NAME_dayOrder = "dayOrder"
    }


}

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_sex} INT," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_ages} INT," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_height} INT," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_weight} INT," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_muscles} INT," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_dayStart} TEXT," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_interval} INT," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_targetCarbohydrates} INT," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_targetWeight} INT);"

private const val SQL_CREATE_ENTRIES2 =
    "CREATE TABLE ${FeedReaderContract.FeedEntry2.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_name} TEXT," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_type} INT," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_protein} FLOAT," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_carbohydrates} FLOAT," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_fibre} FLOAT," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_kilocalorie} FLOAT," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_defaultGram} INT);"

private const val SQL_CREATE_ENTRIES3 =
    "CREATE TABLE ${FeedReaderContract.FeedEntry3.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${FeedReaderContract.FeedEntry3.COLUMN_NAME_date} TEXT," +
            "${FeedReaderContract.FeedEntry3.COLUMN_NAME_foodId} INT," +
            "${FeedReaderContract.FeedEntry3.COLUMN_NAME_gram} INT);"

private const val SQL_CREATE_ENTRIES4 =
    "CREATE TABLE ${FeedReaderContract.FeedEntry4.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${FeedReaderContract.FeedEntry4.COLUMN_NAME_title} TEXT," +
            "${FeedReaderContract.FeedEntry4.COLUMN_NAME_complete} BOOL," +
            "${FeedReaderContract.FeedEntry4.COLUMN_NAME_dateOrder} INT);"

private const val SQL_CREATE_ENTRIES5 =
    "CREATE TABLE ${FeedReaderContract.FeedEntry5.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${FeedReaderContract.FeedEntry5.COLUMN_NAME_name} TEXT," +
            "${FeedReaderContract.FeedEntry5.COLUMN_NAME_dayId } INT," +
            "${FeedReaderContract.FeedEntry5.COLUMN_NAME_weight} INT," +
            "${FeedReaderContract.FeedEntry5.COLUMN_NAME_number} INT," +
            "${FeedReaderContract.FeedEntry5.COLUMN_NAME_setNumber} INT," +
            "${FeedReaderContract.FeedEntry5.COLUMN_NAME_dayOrder} INT);"


private const val SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"
private const val SQL_DELETE_ENTRIES2 =
    "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry2.TABLE_NAME}"
private const val SQL_DELETE_ENTRIES3 =
    "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry3.TABLE_NAME}"
private const val SQL_DELETE_ENTRIES4 =
    "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry4.TABLE_NAME}"
private const val SQL_DELETE_ENTRIES5 =
    "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry5.TABLE_NAME}"

class FeedReaderDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL(SQL_CREATE_ENTRIES2)
        db.execSQL(SQL_CREATE_ENTRIES3)
        db.execSQL(SQL_CREATE_ENTRIES4)
        db.execSQL(SQL_CREATE_ENTRIES5)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        db.execSQL(SQL_DELETE_ENTRIES2)
        db.execSQL(SQL_DELETE_ENTRIES3)
        db.execSQL(SQL_DELETE_ENTRIES4)
        db.execSQL(SQL_DELETE_ENTRIES5)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Schedule.db"
    }
}

fun dbRestart(context: Context) {
    val dbHelper = FeedReaderDbHelper(context)
    val db = dbHelper.writableDatabase
    db.execSQL("DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}")
    db.execSQL("DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry2.TABLE_NAME}")
    db.execSQL("DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry3.TABLE_NAME}")
    db.execSQL("DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry4.TABLE_NAME}")
    db.execSQL("DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry5.TABLE_NAME}")
    db.execSQL(SQL_CREATE_ENTRIES)
    db.execSQL(SQL_CREATE_ENTRIES2)
    db.execSQL(SQL_CREATE_ENTRIES3)
    db.execSQL(SQL_CREATE_ENTRIES4)
    db.execSQL(SQL_CREATE_ENTRIES5)
    db.close()
}
fun selectDiary(date: LocalDate, context: Context) {
    val dbHelper = FeedReaderDbHelper(context)
    val db = dbHelper.readableDatabase
    val c: Cursor =
        db.rawQuery(
            "SELECT * FROM diary WHERE diary.date = '${date}'",
            null)
    DiaryList.removeAll(DiaryList)
    if (c.count != 0) {
        with(c) {
            while (moveToNext()) {
                //val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                //itemIds.add(itemId)
                val item = Diary(id = getString(0).toInt())
                item.date.value = sdf.parse(getString(1)).toInstant().atZone(
                    ZoneId.systemDefault()).toLocalDate()
                item.foodId.value = getInt(2)
                item.gram.value = getInt(3)
                Log.d("count", item.foodId.value.toString())
                DiaryList.add(item)
            }
        }
    }
    c.close()
    db.close()
}
fun addDiary(diary: Diary, context: Context) {
    val dbHelper = FeedReaderDbHelper(context)
    val db = dbHelper.writableDatabase
    val values = ContentValues().apply {
        put(FeedReaderContract.FeedEntry3.COLUMN_NAME_date, diary.date.value.toString())
        put(FeedReaderContract.FeedEntry3.COLUMN_NAME_foodId, diary.foodId.value)
        put(FeedReaderContract.FeedEntry3.COLUMN_NAME_gram, diary.gram.value)
    }
    Log.e("add", diary.foodId.value.toString())
    val newRowId = db?.insert(FeedReaderContract.FeedEntry3.TABLE_NAME, null, values)
    db.close()
}

fun replaceDiary(diary: Diary, context: Context) {
    val dbHelper = FeedReaderDbHelper(context)
    val db = dbHelper.writableDatabase
    val values = ContentValues().apply {
        put(FeedReaderContract.FeedEntry3.COLUMN_NAME_date, diary.date.value.toString())
        put(FeedReaderContract.FeedEntry3.COLUMN_NAME_foodId, diary.foodId.value)
        put(FeedReaderContract.FeedEntry3.COLUMN_NAME_gram, diary.gram.value)
    }
    val c: Cursor =
        db.rawQuery("SELECT * FROM diary WHERE diary._id = '${diary.id}'",
            null)
    if (c.count > 0) {
        db.execSQL("DELETE FROM diary WHERE diary._id = '${diary.id}'")
    }
    val newRowId = db?.insert(FeedReaderContract.FeedEntry3.TABLE_NAME, null, values)
    c.close()
    db.close()
}

fun selectFoods(type: Int, context: Context) {
    val dbHelper = FeedReaderDbHelper(context)
    val db = dbHelper.readableDatabase
    val c: Cursor =
        db.rawQuery(
            "SELECT * FROM foods WHERE foods.type = '${type}'",
            null)
    FoodList.removeAll(FoodList)
    if (c.count != 0) {
        with(c) {
            while (moveToNext()) {
                val item = Food(id = getString(0).toInt())
                item.name.value = getString(1)
                item.type.value = getInt(2)
                item.protein.value = getFloat(3)
                item.carbohydrates.value = getFloat(4)
                item.fibre.value = getFloat(5)
                item.kilocalorie.value = getFloat(6)
                item.defaultGram.value = getInt(7)
                FoodList.add(item)
            }
        }
    }
    c.close()
    db.close()
}
fun selectFood(id: Int, context: Context): Food {
    Log.d("id", id.toString())
    val dbHelper = FeedReaderDbHelper(context)
    val db = dbHelper.readableDatabase
    val c: Cursor =
        db.rawQuery(
            "SELECT * FROM foods WHERE foods._id = '${id}'",
            null)
    val item = Food(0)
    if (c.count != 0) {
        with(c) {
            while (moveToNext()) {
                item.id = getString(0).toInt()
                Log.d("mm", item.id.toString())
                item.name.value = getString(1)
                item.type.value = getInt(2)
                item.protein.value = getFloat(3)
                item.carbohydrates.value = getFloat(4)
                item.fibre.value = getFloat(5)
                item.kilocalorie.value = getFloat(6)
                item.defaultGram.value = getInt(7)
            }
        }
    }
    c.close()
    db.close()
    return item
}

fun addFood(food: Food, context: Context) {
    val dbHelper = FeedReaderDbHelper(context)
    val db = dbHelper.writableDatabase
    val values = ContentValues().apply {
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_name, food.name.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_type, food.type.value.toString())
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_protein, food.protein.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_carbohydrates, food.carbohydrates.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_fibre, food.fibre.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_kilocalorie, food.kilocalorie.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_defaultGram, food.defaultGram.value)
    }
    val newRowId = db?.insert(FeedReaderContract.FeedEntry2.TABLE_NAME, null, values)
    db.close()
}

fun replaceFood(food: Food, context: Context) {
    val dbHelper = FeedReaderDbHelper(context)
    val db = dbHelper.writableDatabase
    val values = ContentValues().apply {
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_name, food.name.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_type, food.type.value.toString())
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_protein, food.protein.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_carbohydrates, food.carbohydrates.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_fibre, food.fibre.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_kilocalorie, food.kilocalorie.value)
        put(FeedReaderContract.FeedEntry2.COLUMN_NAME_defaultGram, food.defaultGram.value)
    }
    val c: Cursor =
        db.rawQuery("SELECT * FROM foods WHERE foods._id = '${food.id}'",
            null)
    if (c.count > 0) {
        db.execSQL("DELETE FROM foods WHERE foods._id = '${food.id}'")
    }
    val newRowId = db?.insert(FeedReaderContract.FeedEntry2.TABLE_NAME, null, values)
    c.close()
    db.close()
}