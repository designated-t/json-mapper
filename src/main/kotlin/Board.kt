import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

interface Board {
    @Composable
    fun load()
}

class ColorBoard: Board {
    @Composable
    override fun load() {
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

}
