package es.esy.gengod.ubernotes

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NoteTest {
    private val note1 = Note()
    private var note2 = Note()

    init {
        note1.title = "Title1"
        note1.description = "Description1"
    }

    @Test
    fun canInitializeNote() {
        assert(note2.description == "" && note2.title == "" && note2.id > 0)
    }

    @Test
    fun canEqualNotes() {
        assert(note1 != note2)
    }

    @Test
    fun canAssignNote() {
        note2.assign(note1)
        assert(note1.title == note2.title && note1.description == note2.description && note1.modifiedOn == note2.modifiedOn)
    }

    @Test
    fun canCopyNote() {
        note2.copy(note1)
        assert(note1 == note2)
    }
}