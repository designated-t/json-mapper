
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

val softPeach = Color.hsl(30f, 1f, 0.85f)     // Soft Peach
val mutedCoral = Color.hsl(16f, 0.7f, 0.7f)   // Muted Coral
val warmSand = Color.hsl(42f, 0.5f, 0.8f)     // Warm Sand
val gentleBlush = Color.hsl(340f, 0.6f, 0.85f) // Gentle Blush
val softMustard = Color.hsl(45f, 0.7f, 0.7f)  // Soft Mustard
val warmTaupe = Color.hsl(30f, 0.2f, 0.6f)    // Warm Taupe
val creamyWhite = Color.hsl(40f, 0.5f, 0.95f) // Creamy White
val mutedClay = Color.hsl(18f, 0.4f, 0.7f)    // Muted Clay

val deepBrown = Color.hsl(30f, 0.35f, 0.20f)     // Dark Brown
val darkChocolate = Color.hsl(30f, 0.45f, 0.30f) // Dark Chocolate Brown
val mocha = Color.hsl(30f, 0.35f, 0.40f)         // Mocha Brown
val warmTaupe2 = Color.hsl(30f, 0.25f, 0.50f)     // Warm Taupe
val latte = Color.hsl(30f, 0.30f, 0.55f)         // Latte Beige
val softBeige = Color.hsl(30f, 0.25f, 0.65f)     // Soft Beige
val creamyWhite2 = Color.hsl(30f, 0.20f, 0.75f)   // Creamy White
val ivory = Color.hsl(30f, 0.15f, 0.85f)         // Ivory

val primary_foreground = latte
val primary_background = mocha
val secondary_foreground = softBeige
val secondary_background = warmTaupe2

private val TOP_SPACER = 5.dp
private val TOP_HEIGHT = 75.dp

@Composable
@Preview
fun App() {
    var selectedMapper by remember { mutableStateOf<Mappers?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = creamyWhite)
    ) {
        Row(
            modifier = Modifier
                .height(TOP_HEIGHT + TOP_SPACER)
                .fillMaxWidth()
        ) {
            Mappers.entries
                .filter { it.enabled }
                .forEach {
                    Button(
                        onClick = { selectedMapper = it },
                        colors = ButtonDefaults.buttonColors(backgroundColor = creamyWhite),
                        shape = RectangleShape,
                        modifier = Modifier
                            .height(TOP_HEIGHT)
                            .weight(1f)
                            .fillMaxWidth(),
                        elevation = Constants.flushButtonElevation()
                    ) {
                        Text("${it.buttonName} Mapper")
                    }
                    Spacer(Modifier.width(TOP_SPACER))
                }
        }


        Spacer(modifier = Modifier.height(16.dp))

        selectedMapper
            ?.load()
            ?: Row {
                Text("Select a Mapper to continue", style = MaterialTheme.typography.h3)
            }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}