package custom

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class ColorsEnumTest {


    @Test
    fun testOrdinal() {
        assertEquals(0, ColorsEnum.RED.ordinal())
        assertEquals(1, ColorsEnum.BLUE.ordinal())
        assertEquals(2, ColorsEnum.GREEN.ordinal())
        assertEquals(3, ColorsEnum.ORANGE.ordinal())
        assertEquals(4, ColorsEnum.YELLOW.ordinal())
        assertEquals(5, ColorsEnum.WHITE.ordinal())
        assertEquals(6, ColorsEnum.BLACK.ordinal())
    }

    @Test
    fun testListWithInstances() {
        val colors =
            listOf(
                ColorsEnum.RED,
                ColorsEnum.BLUE,
                ColorsEnum.GREEN,
                ColorsEnum.ORANGE,
                ColorsEnum.YELLOW,
                ColorsEnum.WHITE,
                ColorsEnum.BLACK
            )
        assertEquals(7, colors.size)
        assertTrue(colors.contains(ColorsEnum.RED))
        assertTrue(colors.contains(ColorsEnum.BLUE))
        assertTrue(colors.contains(ColorsEnum.GREEN))
        assertTrue(colors.contains(ColorsEnum.ORANGE))
        assertTrue(colors.contains(ColorsEnum.YELLOW))
        assertTrue(colors.contains(ColorsEnum.WHITE))
        assertTrue(colors.contains(ColorsEnum.BLACK))
    }
}