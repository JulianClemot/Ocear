package tech.ippon.ocear.ar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.ippon.ocear.common.ArKeyword

@Composable
fun ArSelectModelDialog(selectModel: (keyword: String) -> Unit, arKeywords: List<ArKeyword>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        arKeywords.forEach { arKeyword ->
            Button(
                modifier = Modifier
                    .fillMaxWidth(), onClick = { selectModel(arKeyword.name) }) {
                Text(text = arKeyword.name)
            }
        }
    }
}