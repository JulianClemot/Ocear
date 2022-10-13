package tech.ippon.ocear.ar

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dev.romainguy.kotlin.math.Float3
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.math.Position
import io.github.sceneview.node.Node
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tech.ippon.ocear.common.ArKeyword

class ArViewModel : ViewModel() {

    private val _arScreenUiState = MutableStateFlow<ArSceneViewUiState>(ArSceneViewUiState.Init)
    val arScreenUiState: StateFlow<ArSceneViewUiState>
        get() = _arScreenUiState

    private val _keywordsUiState = MutableStateFlow<KeywordsUiState>(KeywordsUiState.Empty)
    val keywordsUiState: StateFlow<KeywordsUiState>
        get() = _keywordsUiState

    val onModelSelected: (ArSceneView) -> Unit = {
        loadSelectedModel(it)
    }
    val onModelAnchored: (ArSceneView) -> Unit = {
        placeModelNode(it)
    }
    val onModelSelectClick: (ArKeyword) -> Unit = {
        selectModel(it)
    }
    val onModelAnchorClick: () -> Unit = {
        anchorModel()
    }

    private var models: MutableMap<ArKeyword, Model3d> = mutableMapOf()
    private var modelNode: ArModelNode? = null
    lateinit var selectedModel: Model3d

    var chosenColor: Color = Color.Black

    fun initSceneViewWithModels(
        sceneView: ArSceneView,
        arKeywords: List<ArKeyword>
    ) {
        models = getModelsFromArKeywordList(arKeywords)
        sceneView.planeRenderer.isVisible = true // Show plane renderer directly at the beginning
        _keywordsUiState.value = KeywordsUiState.KeywordsRetrieved(arKeywords, chosenColor)
    }

    fun placeModelNode(sceneView: ArSceneView) {
        // Create and anchor this node to make it fixed at the actual position and orientation
        modelNode?.anchor()
        sceneView.planeRenderer.isVisible = false
    }

    fun selectModel(modelName: ArKeyword) {
        models[modelName]?.let {
            selectedModel = it
            _arScreenUiState.value = ArSceneViewUiState.ModelSelected
        }
    }

    fun anchorModel() {
        modelNode?.let {
            _arScreenUiState.value = ArSceneViewUiState.ModelAnchored
        }
    }

    fun loadSelectedModel(sceneView: ArSceneView) {
        destroyPreviousModelNode(sceneView)

        modelNode = ArModelNode(selectedModel.placementMode).apply {
            applyPoseRotation = selectedModel.applyPoseRotation
            loadModelAsync(
                context = sceneView.context,
                lifecycle = sceneView.lifecycle,
                glbFileLocation = selectedModel.fileLocation,
                autoAnimate = true,
                scaleToUnits = selectedModel.scaleUnits,
                // Place the model origin at the bottom center
                centerOrigin = Position(y = -1.0f)
            ) {
                sceneView.planeRenderer.isVisible = true
            }
        }
        modelNode.let {
            sceneView.addChild(it as Node)

            selectedModel.rotationDegrees?.let { rotation ->
                it.modelRotation = Float3(rotation[0], rotation[1], rotation[2])
            }
        }
        // Select the model node by default (the model node is also selected on tap)
        sceneView.selectedNode = modelNode
        _arScreenUiState.value = ArSceneViewUiState.ModelLoaded(chosenColor)
    }

    private fun destroyPreviousModelNode(sceneView: ArSceneView) {
        modelNode?.takeIf { !it.isAnchored }?.let {
            sceneView.removeChild(it)
            it.destroy()
        }
    }

    private fun getModelsFromArKeywordList(arKeywords: List<ArKeyword>) =
        arKeywords.associateWith { it.to3dModel() }.toMutableMap()
}