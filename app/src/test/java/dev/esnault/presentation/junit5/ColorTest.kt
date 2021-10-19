package dev.esnault.presentation.junit5

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class ColorTest {

    @Nested
    @DisplayName("toString()")
    inner class ToString {

        @Test
        @DisplayName("should return #000000 for rgb(0, 0, 0)")
        fun `#000000`() {
            val color = Color(0, 0, 0)
            val result = color.toString()
            assertEquals(expected = "#000000", actual = result)
        }

        @Test
        @DisplayName("should return #FFFFFF for rgb(255, 255, 255)")
        fun `#FFFFFF`() {
            val color = Color(255, 255, 255)
            val result = color.toString()
            assertEquals(expected = "#FFFFFF", actual = result)
        }

        @Test
        @DisplayName("should return #123456 for rgb(18, 52, 86)")
        fun `#123456`() {
            val color = Color(18, 52, 86)
            val result = color.toString()
            assertEquals(expected = "#123456", actual = result)
        }
    }

    @Nested
    @DisplayName("fromString()")
    inner class FromString {

        @Nested
        inner class Valid {

            @Test
            @DisplayName("should parse #000000 as rgb(0, 0, 0)")
            fun `#000000`() {
                val colorString = "#000000"
                val result = Color.fromString(colorString)
                val expected = Color(0, 0, 0)
                assertEquals(expected = expected, actual = result)
            }

            @Test
            @DisplayName("should parse #FFFFFF as rgb(255, 255, 255)")
            fun `#FFFFFF`() {
                val colorString = "#FFFFFF"
                val result = Color.fromString(colorString)
                val expected = Color(255, 255, 255)
                assertEquals(expected = expected, actual = result)
            }

            @Test
            @DisplayName("should parse #123456 as rgb(18, 52, 86)")
            fun `#123456`() {
                val colorString = "#123456"
                val result = Color.fromString(colorString)
                val expected = Color(18, 52, 86)
                assertEquals(expected = expected, actual = result)
            }
        }

        @Nested
        inner class Invalid {

            @Test
            @DisplayName("should throw for an empty string")
            fun `empty string`() {
                val colorString = ""
                assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
            }

            @Test
            @DisplayName("should throw for an invalid format (no #)")
            fun `invalid format (no #)`() {
                val colorString = "FFFFFF"
                assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
            }

            @Test
            @DisplayName("should throw for an invalid format (too short)")
            fun `invalid format (too short)`() {
                val colorString = "#FFF"
                assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
            }

            @Test
            @DisplayName("should throw for an invalid format (too long)")
            fun `invalid format (too long)`() {
                val colorString = "#FFFFFFFF"
                assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
            }

            @Test
            @DisplayName("should throw for an invalid format (not hex)")
            fun `invalid format (not hex)`() {
                val colorString = "#NOTHEX"
                assertThrows<IllegalArgumentException> { Color.fromString(colorString) }
            }
        }
    }
}