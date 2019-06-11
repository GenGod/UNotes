package es.esy.gengod.ubernotes

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
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