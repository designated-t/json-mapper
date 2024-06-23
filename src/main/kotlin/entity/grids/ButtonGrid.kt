package entity.grids

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import entity.Entity
import entity.grids.buttons.AddButton
import entity.grids.buttons.EntityGridButton
import kotlin.math.floor

object ButtonGrid {

    private const val MIN_BUTTON_SIZE = 100 // Minimum button size in dp
    private const val DEFAULT_GRID_COLUMN_NUMBER = 10

    @Composable
    fun make(
        entityList: MutableList<Entity>,
        modifier: Modifier = Modifier,
        onSelect: (Entity, Int) -> Unit,
        onAddButtonClick: () -> Unit,
        windowWidth: Float
    ) {
        val minButtonSizePx = with(LocalDensity.current) { MIN_BUTTON_SIZE.dp.toPx() }
        val maxColumns = floor(windowWidth / minButtonSizePx).toInt()
        val columns = maxColumns.coerceAtMost(DEFAULT_GRID_COLUMN_NUMBER)

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns), // Number of columns
            //verticalArrangement = Arrangement.spacedBy(8.dp),
            //horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.fillMaxSize()
        ) {
            items(entityList.size) { index ->
                EntityGridButton.make(
                    entity = entityList[index],
                    onSelect = { onSelect(entityList[index], index) }
                )
            }

            item {
                AddButton.make(onAddButtonClick)
            }
        }
    }
}