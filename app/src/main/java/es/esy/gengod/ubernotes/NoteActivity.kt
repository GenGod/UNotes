/**
 * @author Bogdan Vetrenko
 */

package es.esy.gengod.ubernotes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class NoteActivity : AppCompatActivity() {

    private lateinit var _noteTitle: EditText
    private lateinit var _noteDescription: EditText
    private lateinit var _saveButton: Button
    private lateinit var _cancelButton: Button
    private lateinit var _removeButton: Button
    private var _note: Note = Note()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        this._noteTitle = findViewById(R.id.activity_note_title)
        this._noteDescription = findViewById(R.id.activity_note_description)
        this._saveButton = findViewById(R.id.activity_note_save_button)
        this._cancelButton = findViewById(R.id.activity_note_cancel_button)
        this._removeButton = findViewById(R.id.activity_note_remove_button)

        val args = intent?.extras?.get("note_data")
        if (args != null) {
                this._note.copy(Gson().fromJson(args.toString(), Note::class.java))
                this._noteTitle.setText(this._note.title)
                this._noteDescription.setText(this._note.description)
        } else {
            this._noteTitle.setText("")
            this._noteDescription.setText("")
        }

        this._saveButton.setOnClickListener {
            val intent = intent
            var resultCode = 0
            if (this._note.title == "" && this._note.description == "") {
                resultCode = 1
            }
            this._note.title = this._noteTitle.text.toString()
            this._note.description = this._noteDescription.text.toString()
            intent.putExtra("note_data", Gson().toJson(this._note, Note::class.java))
            setResult(resultCode, intent)
            finish()
        }

        this._cancelButton.setOnClickListener {
            setResult(2, intent)
            finish()
        }

        this._removeButton.setOnClickListener {
            val intent = intent
            intent.putExtra("id", this._note.id)
            setResult(3, intent)
            finish()
        }
    }
}