package entity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object EntitySidebar {

    private val SPACER = 8.dp
    private val PADDING = 10.dp
    private val TEXT_FIELD_WIDTH = 150.dp
    private val ROW_HEIGHT = 40.dp


    @Composable
    fun make(
        modifier: Modifier = Modifier,
        selectedEntity: Entity?,
        onSave: (Entity) -> Unit
    ) {
        var id by remember(selectedEntity) { mutableStateOf(selectedEntity?.id ?: "") }
        var name by remember(selectedEntity) { mutableStateOf(selectedEntity?.name ?: "") }
        var randomVariable by remember(selectedEntity) { mutableStateOf(selectedEntity?.randomVariable ?: "") }

        Column(
            modifier
                .background(Color.Gray)
                .padding(PADDING)
                .fillMaxHeight()
        ) {
            Text("Entity Properties", style = MaterialTheme.typography.h3, color = Color.White)
            if (selectedEntity != null) {
                Spacer(modifier = Modifier.height(SPACER))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ROW_HEIGHT),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Id:",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .fillMaxHeight(),
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                    OutlinedTextField(
                        value = id,
                        onValueChange = { id = it },
                        modifier = Modifier
                            .width(TEXT_FIELD_WIDTH)
                            .fillMaxHeight()
                            .padding(PADDING),
                        singleLine = true,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ROW_HEIGHT),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Name:",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .fillMaxHeight(),
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        modifier = Modifier
                            .width(TEXT_FIELD_WIDTH)
                            .fillMaxHeight()
                            .padding(PADDING),
                        singleLine = true
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ROW_HEIGHT),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Random:",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .fillMaxHeight(),
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                    OutlinedTextField(
                        value = randomVariable,
                        onValueChange = { randomVariable = it },
                        modifier = Modifier
                            .width(TEXT_FIELD_WIDTH)
                            .fillMaxHeight()
                            .padding(PADDING),
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.height(SPACER))
                Button(
                    onClick = {
                        onSave(Entity(id, name, randomVariable))
                    }
                ) {
                    Text("Save")
                }
            } else {
                Text(
                    "Select an entity to continue...",
                    color = Color.White,
                )
            }

            // Add more actions as needed
        }
    }
}