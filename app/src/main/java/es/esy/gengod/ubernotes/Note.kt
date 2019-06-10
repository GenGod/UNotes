/**
 * @author Bogdan Vetrenko
 */

package es.esy.gengod.ubernotes

import java.util.*

/**
 * @property title Note title
 * @property description Note description
 * @property modifiedOn Note modified time
 * @property id Unique Note id
 * @property Id Counter to create unique Note id
 * @constructor Creates an empty Note with generated id
 */
class Note {

    var title: String
    var description: String
    var modifiedOn: Date
    var id: Long = 0

    companion object {
        var Id: Long = 0
        /**
         * Generates unique Id for new Note
         * @return generated id
         */
        fun generateId(): Long {
            return Id++
        }
    }

    constructor() {
        this.title = ""
        this.description = ""
        this.modifiedOn = Date()
        this.id = generateId()
    }

    /**
     * Copies data from another note
     * @param note Note to full copy
     */
    fun copy(note: Note) {
        this.assign(note)
        this.id = note.id
    }

    /**
     * Assigns data to note
     * @param note Note to assign fields except id
     */
    fun assign(note: Note) {
        this.title = note.title
        this.description = note.description
        this.modifiedOn = note.modifiedOn
    }
}