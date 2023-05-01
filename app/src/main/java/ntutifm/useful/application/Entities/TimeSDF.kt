package ntutifm.useful.application.Entities

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

val sdf = SimpleDateFormat("yyyy-MM-dd")
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM", Locale.TAIWAN)
val formatterGobal: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.TAIWAN)
val formatterTime: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.TAIWAN)