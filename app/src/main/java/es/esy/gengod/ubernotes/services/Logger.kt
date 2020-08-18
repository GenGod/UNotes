package es.esy.gengod.ubernotes.services

import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

class Logger {
    companion object {
        private var ID: Long = 0
        private val logger = Logger.getLogger("UNotesMain")

        fun LogError(title: String, description: String) = logger.log(Level.SEVERE, LogObject(++ID, title, description, Date(), LogType.Error).toString())
        fun LogInfo(title: String, description: String) = logger.log(Level.INFO, LogObject(++ID, title, description, Date(), LogType.Info).toString())
        fun LogWarn(title: String, description: String) = logger.log(Level.WARNING, LogObject(++ID, title, description, Date(), LogType.Warn).toString())
    }
}