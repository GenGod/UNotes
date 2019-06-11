package es.esy.gengod.ubernotes

import org.junit.Test
import com.google.common.truth.Truth.assertThat

class NoteTest {
    private val note1 = Note()
    private var note2 = Note()

    init {
        note1.title = "Title1"
        note1.description = "Description1"
    }

    @Test
    fun canInitializeNote() {
        assertThat(note2.description).isEqualTo("")
        assertThat(note2.title).isEqualTo("")
        assertThat(note2.id).isGreaterThan(0)
    }

    @Test
    fun canEqualNotes() {
        assertThat(note1).isNotEqualTo(note2)
    }

    @Test
    fun canAssignNote() {
        note2.assign(note1)
        assertThat(note1.description).isEqualTo(note2.description)
        assertThat(note1.title).isEqualTo(note2.title)
        assertThat(note1.modifiedOn).isEqualTo(note2.modifiedOn)
        assertThat(note1.id).isNotEqualTo(note2.id)
    }

    @Test
    fun canCopyNote() {
        note2.copy(note1)
        assertThat(note1).isEqualTo(note2)
    }
}