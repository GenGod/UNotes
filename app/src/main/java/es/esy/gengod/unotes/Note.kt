/**
 * @author Bogdan Vetrenko
 */

package es.esy.gengod.unotes

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
    var id: Int = 0

    companion object {
        var Id: Int = 0
        /**
         * Generates unique Id for new Note
         * @return generated id
         */
        fun generateId(): Int {
            return Id++
        }
    }

    init {
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

    override fun equals(other: Any?): Boolean {
        return when {
            other == null -> false
            other::class.java != Note::class.java -> false
            (other as Note).title != this.title -> false
            other.description != this.description -> false
            other.id != this.id -> false
            else -> true
        }
    }

    override fun hashCode(): Int {
        var hash = id
        for (i in title.toByteArray()) {
            hash += i
        }
        for (i in description.toByteArray()) {
            hash += i
        }
        hash += modifiedOn.time.toInt()

        return hash
    }

    override fun toString(): String {
        return "$id: $title - $description at $modifiedOn"
    }
}