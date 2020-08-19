/**
 * @author Bogdan Vetrenko
 */

package es.esy.gengod.unotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import es.esy.gengod.unotes.services.NotesSerializer
import es.esy.gengod.unotes.services.Logger
import java.io.*
import java.lang.Exception
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var _notes: ArrayList<Note> = ArrayList()
    private var _notesToDelete: ArrayList<Note> = ArrayList()
    private val toastDuration = Toast.LENGTH_SHORT
    private lateinit var _listView: GridLayout
    private lateinit var _createNewNoteButton: Button
    private lateinit var _removeSelectedNotesButton: Button

    companion object {
        const val FILENAME = "NotesStore.dat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        this._listView = findViewById(R.id.notes_list)
        this._createNewNoteButton = findViewById(R.id.activity_main_create_new_note_button)
        this._removeSelectedNotesButton = findViewById(R.id.activity_main_remove_selected_notes_button)

        initializeNotes()
        showGridLayout()

        this._createNewNoteButton.setOnClickListener {
            this.openNote(Note())
        }

        this._removeSelectedNotesButton.setOnClickListener {
            this.removeSelectedNotes()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var toastDescription = ""
        try {
            if (data != null) {
                if (resultCode == 0) {
                    if (this.updateNode(Gson().fromJson(data.extras?.get("note_data").toString(), Note::class.java))) {
                        toastDescription = resources.getString(R.string.toast_saved)
                        Runnable {
                            this.updateNotesOnDisk()
                        }.run()
                    }
                } else if (resultCode == 1) {
                    this.appendNode(Gson().fromJson(data.extras?.get("note_data").toString(), Note::class.java))
                    toastDescription = resources.getString(R.string.toast_added)
                    Runnable {
                        this.updateNotesOnDisk()
                    }.run()
                } else if (resultCode == 3) {
                    this.removeNote(Gson().fromJson(data.extras?.get("id").toString(), Int::class.java))
                    toastDescription = resources.getString(R.string.toast_removed)
                    Runnable {
                        this.updateNotesOnDisk()
                    }.run()
                }
            }
        } catch (exception: Exception) {
            Logger.logError("onActivityResult", "${exception.message}. ${exception.stackTrace}")
            toastDescription = resources.getString(R.string.toast_note_error)
        } finally {
            if (toastDescription != "") {
                val toast = Toast.makeText(applicationContext, toastDescription, toastDuration)
                toast.show()
            }
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRestart() {
        super.onRestart()
        this._listView.removeAllViews()
        showGridLayout()
    }

    override fun onBackPressed() {
        if (this._notesToDelete.size == 0) {
            super.onBackPressed()
        } else {
            this._notesToDelete.clear()
        }
    }

    /**
     * Reads notes from internal storage
     */
    private fun initializeNotes(): Boolean {
        val errorTag = "initializeNotes Error"
        try {
            val inFile = applicationContext.openFileInput(FILENAME)
            inFile.use {
                val serializedString = String(it.readBytes())
                Log.w("initializeNotes()", serializedString)
                this._notes = NotesSerializer.deserializeNotes(serializedString)
            }
            return true
        } catch (exception: FileNotFoundException) {
            Logger.logError(errorTag, exception.message!!)
            return false
        } catch (exception: IOException) {
            Logger.logError(errorTag, exception.message!!)
            return false
        } catch (exception: Exception) {
            Logger.logError(errorTag, exception.message!!)
            return false
        }
    }

    /**
     * Updates all notes in internal storage
     */
    private fun updateNotesOnDisk(): Boolean {
        val errorTag = "updateNotesOnDisk Error"
        try {
            val file = applicationContext.openFileOutput(FILENAME, Context.MODE_PRIVATE)
            val serializedString = NotesSerializer.serializeNotes(this._notes)
            file.use { outFile ->
                outFile.bufferedWriter().use {
                    it.write(serializedString)
                }
            }
            return true
        } catch (exception: FileNotFoundException) {
            Logger.logError(errorTag, exception.message!!)
            return false
        } catch (exception: IOException) {
            Logger.logError(errorTag, exception.message!!)
            return false
        } catch (exception: Exception) {
            Logger.logError(errorTag, exception.message!!)
            return false
        }
    }

    /**
     * Shows note list in GridLayout
     */
    private fun showGridLayout() {
        val layoutInflater = layoutInflater
        this._notes.sortByDescending {it.modifiedOn}
        if (this._notes.count() == 0) {
            val placeholderView = layoutInflater.inflate(R.layout.notes_placeholder, this._listView, false)
            this._listView.addView(placeholderView)
        } else {
            for (note in this._notes) {
                val view = layoutInflater.inflate(R.layout.note_item, this._listView, false)
                view.id = note.id
                val noteTitle = view.findViewById<TextView>(R.id.note_title)
                val noteDescription = view.findViewById<TextView>(R.id.note_description)
                var title: String

                if (note.title.length > 44) {
                    title = note.title.substring(0, 44)
                    title += "..."
                } else {
                    title = note.title
                }

                noteTitle.text = title

                var description = note.description

                if (description.length > 110) {
                    description = description.substring(0, 110)
                    description += "..."
                }

                noteDescription.text = description

                view.setOnClickListener {
                    openNote(note)
                }

                view.isLongClickable = true

                view.setOnLongClickListener {
                    try {
                        if (this._notesToDelete.contains(note)) {
                            this._notesToDelete.remove(note)
                            it.setBackgroundResource(R.drawable.border)
                            true
                        } else {
                            this._notesToDelete.add(note)
                            it.setBackgroundResource(R.drawable.border_bold)
                            true
                        }
                    } catch (exception: Exception) {
                        false
                    }
                }

                this._listView.addView(view)
            }
        }
    }

    /**
     * Opens activity with note data
     * @param note Note to get data to open activity
     */
    private fun openNote(note: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("note_data", Gson().toJson(note, Note::class.java))
        startActivityForResult(intent, 1)
    }

    /**
     * Update note in note list
     * @param noteToUpdate Note to update data in note list
     * @return Update status
     */
    private fun updateNode(noteToUpdate: Note): Boolean {
        for (note in this._notes) {
            if (note.id == noteToUpdate.id) {
                note.assign(noteToUpdate)
                return true
            }
        }

        return false
    }

    /**
     * Appends new note to note list
     * @param noteToAppend Note to append to note list
     */
    private fun appendNode(noteToAppend: Note) {
        this._notes.add(noteToAppend)
    }

    /**
     * Removes note from note list by id
     * @param id Note id
     */
    private fun removeNote(id: Int) {
        for (i in 0 until this._notes.size) {
            if (this._notes[i].id == id) {
                this._notes.removeAt(i)
                break
            }
        }
    }

    /**
     * Removes notes from note list
     */
    private fun removeSelectedNotes() {
        for (i in 0 until this._notesToDelete.size) {
            this._notes.remove(this._notesToDelete[i])
            this._listView.removeView(findViewById<View>(this._notesToDelete[i].id))
        }

        if (this.updateNotesOnDisk()) {
            val toast = Toast.makeText(applicationContext, resources.getString(R.string.toast_removed), toastDuration)
            toast.show()
        }
    }
}