package es.esy.gengod.unotes

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import es.esy.gengod.unotes.services.NotesSerializer
import java.util.*

class UtilsTest {

    @Test
    fun canSerializeEmptyNotes() {
        assertThat(NotesSerializer.serializeNotes(ArrayList())).isEqualTo("")
    }

    @Test
    fun canDeserializeEmptyNote() {
        assertThat(NotesSerializer.deserializeNotes("")).isEqualTo(ArrayList<Note>())
    }

}