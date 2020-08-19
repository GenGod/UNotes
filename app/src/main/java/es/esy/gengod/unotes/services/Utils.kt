/**
 * @author Bogdan Vetrenko
 */

package es.esy.gengod.unotes.services

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.esy.gengod.unotes.Note

class NotesSerializer {
    companion object {
        /**
         * Serialize notes arrayList to JSON
         * @param notes The ArrayList of notes to serialize
         * @return JSON string
         */
        fun serializeNotes(notes: ArrayList<Note>): String {
            if (notes.size == 0) {
                return ""
            }
            return Gson().toJson(notes, object : TypeToken<ArrayList<Note>>() {}.type)
        }

        /**
         * Deserialize notes from JSON to Note arrayList
         * @param serializedString JSON string of serialized notes
         * @return ArrayList of notes
         */
        fun deserializeNotes(serializedString: String): ArrayList<Note> {
            if (serializedString == "") {
                return ArrayList()
            }
            return Gson().fromJson(serializedString, object : TypeToken<ArrayList<Note>>() {}.type)
        }
    }
}