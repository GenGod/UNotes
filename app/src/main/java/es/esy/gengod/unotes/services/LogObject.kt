package es.esy.gengod.unotes.services

import java.util.*

enum class LogType {
    Info,
    Error,
    Warn
}

class LogObject(private val id: Long, private val title: String, private val description: String, private val timestamp: Date, private val type: LogType) {
    override fun toString(): String {
        val logType = when(type) {
            LogType.Error -> "Error"
            LogType.Info -> "Info"
            LogType.Warn -> "Warn"
        }

        return "$logType log with $id at $timestamp. $title: $description"
    }
}