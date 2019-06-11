package es.esy.gengod.ubernotes

import org.junit.Assert
import org.junit.Test

class UtilsTest {

    @Test
    fun canSerializeEmptyNotes() {
        Assert.assertEquals(serializeNotes(ArrayList()), "")
    }

    @Test
    fun canDeserializeEmptyNote() {
        Assert.assertEquals(deserializeNotes(""), ArrayList<Note>())
    }

}