
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
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

data class Entity(
    var id: String = "",
    var name: String = "",
)

enum class Mappers(val buttonName: String, val enabled: Boolean = false) {
    ENTITY("Entities", true),
    QUEST("Quests", true),
    MAP("Maps")
    ;
}


@Composable
@Preview
fun App() {
    val entities = remember { mutableStateListOf<Entity>() }

    var selectedEntity by remember { mutableStateOf<Entity?>(null) }
    var selectedMapper by remember { mutableStateOf<Mappers?>(null) }

    var idContent by remember { mutableStateOf("") }
    var nameContent by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(creamyWhite)
    ) {
        Row(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth()
        ) {
            Mappers.entries.forEach {
                Button(
                    onClick = { selectedMapper = it },
                    modifier = Modifier
                        .heightIn(min = 75.dp)
                        .weight(1f),
                    enabled = it.enabled,
                    colors = ButtonDefaults.buttonColors(backgroundColor = mocha),
                    elevation = null
                ) {
                    Text("${it.buttonName} Mapper")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedMapper) {
            Mappers.ENTITY -> {
                Row {
                    Sidebar(
                        entity = selectedEntity,
                        idContent = idContent,
                        nameContent = nameContent,
                        onIdValueChange = {
                            idContent = it
                        },
                        onNameValueChange = {
                            nameContent = it
                        },
                        onSave = {
                            entities[entities.indexOf(selectedEntity)] = Entity(idContent, nameContent)
                        }
                    )

                    Grid(
                        entities,
                        onAddItem = {
                            entities.add(
                                Entity()
                            )
                        },
                        onSelectItem = { index ->
                            selectedEntity = entities[index]
                            idContent = selectedEntity?.id ?: ""
                            nameContent = selectedEntity?.name ?: ""
                        }
                    )
                }
            }

            Mappers.MAP -> {

            }

            Mappers.QUEST -> {
                Row {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(300.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(UIColours.entries.size) { index ->
                            Text(
                                text = UIColours.entries[index].colourName,
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(300.dp)
                                    .padding(8.dp)
                                    .background(UIColours.entries[index].colour),
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }

            else -> {
                Row {
                    Text("Select a Mapper to continue", style = MaterialTheme.typography.h3)
                }
            }
        }
    }
}

@Composable
fun Sidebar(
    entity: Entity?,
    idContent: String,
    nameContent: String,
    onIdValueChange: (String) -> Unit,
    onNameValueChange: (String) -> Unit,
    onSave: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight(),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .betterShadow()
                .height(75.dp)
                .fillMaxWidth()
                .background(
                    color = mocha,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = deepBrown
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Entity Properties",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .betterShadow()
                .fillMaxSize()
                .background(
                    color = mocha,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = deepBrown
                )
        ) {
            if (entity != null) {
                TextField(
                    value = idContent,
                    onValueChange = { onIdValueChange(it) },
                    label = { Text("Id") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = nameContent,
                    onValueChange = { onNameValueChange(it) },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        onSave()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = entity.name != nameContent || entity.id != idContent
                ) {
                    Text("Save")
                }
            } else {
                Text("Select an Entity to continue...", style = MaterialTheme.typography.subtitle2)
            }
        }
    }
}

@Composable
fun Grid(
    entities: List<Entity>,
    onAddItem: () -> Unit,
    onSelectItem: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .defaultMinSize(minWidth = 500.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .betterShadow()
                .height(75.dp)
                .background(
                    color = mocha,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = deepBrown
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                "Entities",
                modifier = Modifier.weight(1f).padding(horizontal = 16.dp).fillMaxWidth(),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
            )

            Button(
                onClick = onAddItem,
                modifier = Modifier.weight(3f).padding(16.dp).fillMaxSize(),
                colors = ButtonDefaults.buttonColors(backgroundColor = mutedCoral),
                shape = RectangleShape
            ) {
                Text("Add New Entity")
            }

        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = Modifier
                .padding(16.dp)
                .betterShadow()
                .fillMaxSize()
                .background(
                    color = mocha,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = deepBrown
                )
        ) {
            items(entities.size) { index ->
                val item = entities[index]
                val colour = UIColours.entries.random().colour
                Button(
                    onClick = { onSelectItem(index) },
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colour),
                    shape = RoundedCornerShape(4.dp),
                    elevation = null,
                ) {
                    Text(item.id.ifEmpty { "Edit" })
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

fun Modifier.betterShadow() =
    this
        .shadow(
            8.dp,
            shape = RoundedCornerShape(12.dp),
            clip = false
        )


