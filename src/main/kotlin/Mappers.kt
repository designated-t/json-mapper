import androidx.compose.runtime.Composable

enum class Mappers(
    val buttonName: String,
    val enabled: Boolean = false,
    private val board: Board? = null) {
    ENTITY("Entities", true, EntityBoard()),
    QUEST("Quests", false, ),
    MAP("Maps", true, ),

    COLOR("Colours", true, ColorBoard()),
    ;

    @Composable
    fun load() {
        board?.load()
    }
}