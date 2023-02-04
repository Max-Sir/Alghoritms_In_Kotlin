package custom

class ColorsEnum  // Private constructor
private constructor() {
    fun ordinal(): Int {
        val ColorsEnum = values
        for (i in ColorsEnum.indices) {
            if (this === ColorsEnum[i]) return i
        }
        return -1
    }

    companion object {
        // Public static method to get the single instance of the class
        // Private static member to hold the single instance of ColorsEnum
        var instance: ColorsEnum? = null
            get() {
                if (field == null) {
                    field = ColorsEnum()
                }
                return field
            }
            private set

        val values: Array<ColorsEnum>
            get() = arrayOf(RED, BLUE, GREEN, ORANGE, YELLOW, WHITE, BLACK)

        // Enum like constants
        val RED = ColorsEnum()
        val BLUE = ColorsEnum()
        val GREEN = ColorsEnum()
        val ORANGE = ColorsEnum()
        val YELLOW = ColorsEnum()
        val WHITE = ColorsEnum()
        val BLACK = ColorsEnum()
    }
}