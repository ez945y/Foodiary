package ntutifm.useful.application.Entities

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns


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
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_protein} INT," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_carbohydrates} INT," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_fibre} INT," +
            "${FeedReaderContract.FeedEntry2.COLUMN_NAME_kilocalorie} INT," +
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

fun dbReStart(context: Context) {
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
