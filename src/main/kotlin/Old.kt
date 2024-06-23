@file:JvmName("SecondMainKt")

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import entity.Entity
import entity.EntitySidebar
import entity.grids.ButtonGrid

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        Main.App(this.window)
    }
}

val SIDEBAR_MIN_WIDTH = 240.dp
val SIDEBAR_MAX_WIDTH = 370.dp


object Main {
    @Composable
    @Preview
    fun App(window: ComposeWindow) {
        var selectedEntity by remember { mutableStateOf<Entity?>(null) }
        var selectedIndex by remember { mutableStateOf(-1) }
        val entityList = remember { mutableStateListOf<Entity>() }

        Row(Modifier.fillMaxSize()) {
            EntitySidebar.make(
                Modifier
                    .widthIn(SIDEBAR_MIN_WIDTH, SIDEBAR_MAX_WIDTH),
                selectedEntity,
                onSave = { newData ->
                    if (selectedIndex != -1) {
                        entityList[selectedIndex] = newData
                        selectedEntity = newData
                    }
                }
            )
            ButtonGrid.make(
                entityList,
                Modifier.weight(5f),
                onSelect = { newSelection, index ->
                    selectedEntity = newSelection
                    selectedIndex = index
                },
                onAddButtonClick = {
                    entityList.add(Entity())
                },
                window.bounds.width.toFloat()
            )
        }
    }
}