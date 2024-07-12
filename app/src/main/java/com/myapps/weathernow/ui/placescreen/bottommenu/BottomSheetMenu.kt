package com.myapps.weathernow.ui.placescreen.bottommenu

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.myapps.weathernow.R
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetMenu(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    listOfMenuItemsData: List<BottomSheetItemData>,
    containerColor: Color
) {
    val modalSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = containerColor,
    ) {
        LazyColumn {
            items(listOfMenuItemsData) { data ->
                BottomSheetItem(
                    modifier = Modifier
                        .padding(15.dp), icon = data.icon, contentText = data.contentText
                ) {
                    data.actionPerformed()
                }
            }
        }
    }
}
