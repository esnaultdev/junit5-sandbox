package dev.esnault.presentation.junit5

import java.util.*
import kotlin.IllegalArgumentException

/**
 * A data class representing a RGB color.
 * @property red The red component of the color. Between 0 and 255.
 * @property green The green component of the color. Between 0 and 255.
 * @property blue The blue component of the color. Between 0 and 255.
 */
data class Color(
    val red: Int,
    val green: Int,
    val blue: Int,
) {

    init {
        require(red in validRange)
        require(green in validRange)
        require(blue in validRange)
    }

    /**
     * Returns the color in the #RRGGBB format.
     */
    override fun toString(): String {
        fun hex(value: Int): String = value.toString(radix = 16)
            .padStart(length = 2, padChar = '0')
            .toUpperCase(Locale.ROOT)
        return "#${hex(red)}${hex(green)}${hex(blue)}"
    }

    companion object {
        private val validRange = 0..255

        /**
         * Parse the color string, and return the corresponding color. If the string cannot be
         * parsed, throws an [IllegalArgumentException] exception. Supported formats is #RRGGBB.
         */
        @Throws(IllegalArgumentException::class)
        fun fromString(colorString: String): Color {
            if (colorString.length != 7 || colorString[0] != '#') {
                throw IllegalArgumentException("Invalid color format")
            }

            return Color(
                red = colorString.substring(1, 3).toInt(16),
                green = colorString.substring(3, 5).toInt(16),
                blue = colorString.substring(5, 7).toInt(16),
            )
        }
    }
}