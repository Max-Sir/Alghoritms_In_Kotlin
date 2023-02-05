// Making the ColorsEnum class ThreadSafe
class ColorsEnum  // Private constructor
private constructor() {
    fun ordinal(): Int {
        val colorsEnum = values
        for (i in colorsEnum.indices) {
            if (this === colorsEnum[i]) return i
        }
        return -1
    }

    companion object {
        // Public static method to get the single instance of the class
        // Private static member to hold the single instance of ColorsEnum
        @Volatile
        private var instance: ColorsEnum? = null

        @Synchronized
        fun getInstance(): ColorsEnum? {
            if (instance == null) {
                instance = ColorsEnum()
            }
            return instance
        }

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