package dev.esnault.presentation.junit5

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class ColorTest {

    // region toString

    @Test
    fun `toString should return #000000`() {
        val color = Color(0, 0, 0)
        val result = color.toString()
        assertEquals(expected = "#000000", actual = result)
    }

    @Test
    fun `toString should return #FFFFFF`() {
        val color = Color(255, 255, 255)
        val result = color.toString()
        assertEquals(expected = "#FFFFFF", actual = result)
    }

    @Test
    fun `toString should return #123456`() {
        val color = Color(18, 52, 86)
        val result = color.toString()
        assertEquals(expected = "#123456", actual = result)
    }

    // endregion

    // region fromString

    @Test
    fun `fromString should parse #000000`() {
        val colorString = "#000000"
        val result = Color.fromString(colorString)
        val expected = Color(0, 0, 0)
        assertEquals(expected = expected, actual = result)
    }

    @Test
    fun `fromString should parse #FFFFFF`() {
        val colorString = "#FFFFFF"
        val result = Color.fromString(colorString)
        val expected = Color(255, 255, 255)
        assertEquals(expected = expected, actual = result)
    }

    @Test
    fun `fromString should parse #123456`() {
        val colorString = "#123456"
        val result = Color.fromString(colorString)
        val expected = Color(18, 52, 86)
        assertEquals(expected = expected, actual = result)
    }

    @Test
    fun `fromString should throw for an empty string`() {
        val colorString = ""
        assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
    }

    @Test
    fun `fromString should throw for an invalid format (no #)`() {
        val colorString = "FFFFFF"
        assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
    }

    @Test
    fun `fromString should throw for an invalid format (too short)`() {
        val colorString = "#FFF"
        assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
    }

    @Test
    fun `fromString should throw for an invalid format (too long)`() {
        val colorString = "#FFFFFFFF"
        assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
    }

    @Test
    fun `fromString should throw for an invalid format (not hex)`() {
        val colorString = "#NOTHEX"
        assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
    }

    // endregion
}