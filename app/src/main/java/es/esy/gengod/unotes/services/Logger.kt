package es.esy.gengod.unotes.services

import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

class Logger {
    companion object {
        private var ID: Long = 0
        private val logger = Logger.getLogger("UNotesMain")

        fun logError(title: String, description: String) = logger.log(Level.SEVERE, LogObject(++ID, title, description, Date(), LogType.Error).toString())
        fun logInfo(title: String, description: String) = logger.log(Level.INFO, LogObject(++ID, title, description, Date(), LogType.Info).toString())
        fun logWarn(title: String, description: String) = logger.log(Level.WARNING, LogObject(++ID, title, description, Date(), LogType.Warn).toString())
    }
}