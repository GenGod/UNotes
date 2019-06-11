package es.esy.gengod.ubernotes

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import java.util.*

class UtilsTest {

    @Test
    fun canSerializeEmptyNotes() {
        assertThat(serializeNotes(ArrayList())).isEqualTo("")
    }

    @Test
    fun canDeserializeEmptyNote() {
        assertThat(deserializeNotes("")).isEqualTo(ArrayList<Note>())
    }

}