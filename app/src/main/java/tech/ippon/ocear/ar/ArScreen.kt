package tech.ippon.ocear.ar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import io.github.sceneview.ar.ArSceneView
import tech.ippon.ocear.R
import tech.ippon.ocear.common.ArKeyword

@Composable
fun ArScreen(
    sceneViewUiState: ArSceneViewUiState,
    keywordsUiState: KeywordsUiState,
    onSceneView: (ArSceneView) -> Unit,
    onModelSelected: (ArSceneView) -> Unit,
    onModelAnchored: (ArSceneView) -> Unit,
    onModelSelectClick: (arKeyword: ArKeyword) -> Unit,
    onModelAnchorClick: () -> Unit,
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        update = {
            when (sceneViewUiState) {
                is ArSceneViewUiState.ModelSelected -> onModelSelected(it)
                is ArSceneViewUiState.ModelAnchored -> onModelAnchored(it)
                else -> Unit
            }
        },
        factory = { context ->
            ArSceneView(context).apply {
                onSceneView(this)
            }
        }
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (sceneViewUiState is ArSceneViewUiState.ModelLoaded) {
            Button(
                onClick = { onModelAnchorClick() },
                colors = ButtonDefaults.buttonColors(sceneViewUiState.chosenColor)
            )
            { Text(stringResource(id = R.string.place_object)) }
        }
        if (keywordsUiState is KeywordsUiState.KeywordsRetrieved) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            ) {
                items(keywordsUiState.keywords) { modelName ->
                    Button(
                        modifier = Modifier.padding(4.dp),
                        colors = ButtonDefaults.buttonColors(keywordsUiState.chosenColor),
                        onClick = { onModelSelectClick(modelName) },
                    )
                    { Text(text = modelName.label, fontSize = 18.sp, fontWeight = FontWeight.Bold) }
                }
            }
        }
    }
}