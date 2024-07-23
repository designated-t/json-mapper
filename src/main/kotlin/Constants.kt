import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object Constants {

    @Composable
    fun flushButtonElevation() =
        ButtonDefaults.elevation(
            defaultElevation = 4.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 8.dp,
            focusedElevation = 0.dp
        )


}