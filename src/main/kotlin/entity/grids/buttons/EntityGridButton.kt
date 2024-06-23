package entity.grids.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import entity.Entity

object EntityGridButton {

    @Composable
    fun make(onSelect: () -> Unit, entity: Entity) {

        Button(
            onClick = { onSelect() },
            modifier = Modifier
                .aspectRatio(1f) // Make button square
                .fillMaxSize()
                .border(BorderStroke(1.dp, Color.Black)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.LightGray,
                contentColor = Color.White
            )
        ) {
            if (entity.id.isNotEmpty())
                Text(
                    text = "${entity.id.first()}",
                    style = MaterialTheme.typography.h5
                )
        }
    }
}