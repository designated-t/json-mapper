package entity.grids.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object AddButton {

    @Composable
    fun make(onAddButtonClick: () -> Unit) {
        Button(
            onClick = { onAddButtonClick() },
            modifier = Modifier
                .aspectRatio(1f) // Make button square
                .fillMaxSize()
                .border(BorderStroke(1.dp, Color.Black))
                .padding(2.dp),
        ) {
            Text(
                text = "+",
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.h3
            )
        }
    }

}