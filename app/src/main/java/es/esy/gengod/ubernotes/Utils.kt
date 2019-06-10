/**
 * @author Bogdan Vetrenko
 */

package es.esy.gengod.ubernotes

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Serialize notes arrayList to JSON
 * @param notes The ArrayList of notes to serialize
 * @return JSON string
 */
fun serializeNotes(notes: ArrayList<Note>): String {
    return Gson().toJson(notes, object : TypeToken<ArrayList<Note>>() {}.type)
}

/**
 * Deserialize notes from JSON to Note arrayList
 * @param serializedString JSON string of serialized notes
 * @return ArrayList of notes
 */
fun deserializeNotes(serializedString: String): ArrayList<Note> {
    return Gson().fromJson(serializedString, object : TypeToken<ArrayList<Note>>() {}.type)
}