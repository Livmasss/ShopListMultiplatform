package utils

import com.livmas.my_collections_app.utils.removeExtraSpaces
import kotlin.test.Test
import kotlin.test.assertEquals

class StringExtensionTest {
    @Test
    fun removeExtraSpaces_noSpaces() {
        val srcString = "String"
        val result = srcString.removeExtraSpaces()

        assertEquals(srcString, result)
    }
    @Test
    fun removeExtraSpaces_correctSpaces() {
        val srcString = "String with spaces"
        val result = srcString.removeExtraSpaces()

        assertEquals(srcString, result)
    }
    @Test
    fun removeExtraSpaces_firstSpace() {
        val srcString = " String"
        val expectedString = "String"
        val result = srcString.removeExtraSpaces()

        assertEquals(expectedString, result)
    }
    @Test
    fun removeExtraSpaces_firstSpaces() {
        val srcString = "   String"
        val expectedString = "String"
        val result = srcString.removeExtraSpaces()

        assertEquals(expectedString, result)
    }
    @Test
    fun removeExtraSpaces_centerSpaces() {
        val srcString = "String    with   spaces"
        val expectedString = "String with spaces"
        val result = srcString.removeExtraSpaces()

        assertEquals(expectedString, result)
    }
    @Test
    fun removeExtraSpaces_endSpaces() {
        val srcString = "String    "
        val expectedString = "String"
        val result = srcString.removeExtraSpaces()

        assertEquals(expectedString, result)
    }
    @Test
    fun removeExtraSpaces_startCenterEndSpaces() {
        val srcString = "   String   with   spaces    "
        val expectedString = "String with spaces"
        val result = srcString.removeExtraSpaces()

        assertEquals(expectedString, result)
    }
}