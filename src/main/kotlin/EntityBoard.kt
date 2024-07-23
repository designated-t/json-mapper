
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import entity.Entity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class EntityBoard : Board {

    private val STD_ELEVATION = 12.dp
    private val STD_CORNER = 12.dp

    private var entities: MutableList<Entity> = getFromFile()
    private var selectedEntity: Entity? = null

    @Composable
    override fun load() {
        remember { mutableStateListOf(entities) }
        remember { mutableStateOf(selectedEntity) }
        var idContent by remember { mutableStateOf("") }
        var nameContent by remember { mutableStateOf("") }
        var exceptionMessage: String? by remember { mutableStateOf(null) }

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
                    when {
                        idContent.isEmpty() -> exceptionMessage = "The mandatory Identifier for this object is empty or null"
                        idContent in entities.map { it.id } -> exceptionMessage = "The mandatory Identifier for this object is duplicated in one or more objects"
                        else -> {
                            val entityToSave = Entity(
                                id = idContent,
                                name = nameContent
                            )
                            entities[entities.indexOf(selectedEntity)] = entityToSave
                            selectedEntity = entityToSave
                        }
                    }
                },
            )

            ExceptionDialog(exceptionMessage) { exceptionMessage = null }

            Grid(
                entities = entities,
                onAddItem = {
                    entities.add(
                        Entity()
                    )
                },
                onSelectItem = { index ->
                    selectedEntity = entities[index]
                    idContent = selectedEntity?.id ?: ""
                    nameContent = selectedEntity?.name ?: ""
                },
                onThrow = {
                    exceptionMessage = it
                }
            )
        }
    }

    @Composable
    private fun Sidebar(
        entity: Entity?,
        idContent: String,
        nameContent: String,
        onIdValueChange: (String) -> Unit,
        onNameValueChange: (String) -> Unit,
        onSave: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .width(320.dp)
                .fillMaxHeight(),
        ) {
            Surface(
                elevation = STD_ELEVATION,
                shape = RoundedCornerShape(STD_CORNER),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                color = softBeige,
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Entity Properties",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.size(16.dp))

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
                        Text(
                            "Select an Entity to continue...",
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun Grid(
        entities: List<Entity>,
        onAddItem: () -> Unit,
        onSelectItem: (Int) -> Unit,
        onThrow: (String?) -> Unit,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Surface(
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(75.dp),
                color = softBeige,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Entities",
                        modifier = Modifier.weight(1f).padding(16.dp).fillMaxWidth(),
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )

                    Button(
                        onClick = onAddItem,
                        elevation = Constants.flushButtonElevation(),
                        modifier = Modifier.weight(1.5f).padding(16.dp).fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Add New Entity")
                    }

                    Button(
                        onClick =  { try { saveToFile(entities) } catch(e: Throwable) { onThrow(e.message) } },
                        elevation = Constants.flushButtonElevation(),
                        modifier = Modifier.weight(1.5f).padding(16.dp).fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Save current Entities")
                    }
                }

            }

            Surface(
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                color = softBeige,
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(entities.size) { index ->
                        val item = entities[index]
                        val colour = creamyWhite
                        Button(
                            onClick = { onSelectItem(index) },
                            modifier = Modifier
                                .width(150.dp)
                                .height(150.dp)
                                .padding(16.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = colour),
                            shape = RoundedCornerShape(4.dp),
                            elevation = Constants.flushButtonElevation(),
                        ) {
                            Text(item.id.ifEmpty { "Edit" })
                        }
                    }
                }
            }
        }
    }

    private fun saveToFile(entities: Collection<Entity>) {
        filePrettyNaming(entities)

        entities.forEach {
            File(".\\src\\main\\resources\\entities\\${it.fileName}.json")
                .writeText(Json.encodeToString(it))
        }

    }

    private fun filePrettyNaming(entities: Collection<Entity>, suffix: Int? = null) {
        val usableSuffix = if (suffix == null) "" else "_$suffix"

        entities.forEach { entity ->
            entity.fileName = entity.id + usableSuffix
        }

        if (entities.any { upperEntity -> entities.filter { it.fileName == upperEntity.fileName }.size > 1 })
            filePrettyNaming(entities, suffix ?: 1)

        entities.forEach { it.fileName = it.fileName.replace(" ", "_").lowercase() }
    }

    private fun getFromFile(): MutableList<Entity> = Files.walk(
        Paths.get(".\\src\\main\\resources\\entities\\")
    ).use { paths ->
        paths
            .filter(Files::isRegularFile)
            .map { File(it.toString()).bufferedReader().readText() }
            .toList()
    }.map {
        Json.decodeFromString<Entity>(it)
    }.toMutableStateList()

    @Composable
    fun ExceptionDialog(exceptionMessage: String?, onDismiss: () -> Unit) {
        if (exceptionMessage != null) {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = {
                    Text(text = "Exception Occurred")
                },
                text = {
                    Text(text = exceptionMessage)
                },
                confirmButton = {
                    Button(onClick = onDismiss) {
                        Text("OK")
                    }
                }
            )
        }
    }
}



