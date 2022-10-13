package tech.ippon.ocear.ar

import dev.romainguy.kotlin.math.Float3
import io.github.sceneview.ar.node.PlacementMode

data class Model3d(
    val label: String,
    val fileLocation: String,
    val scaleUnits: Float? = null,
    val placementMode: PlacementMode = PlacementMode.BEST_AVAILABLE,
    val applyPoseRotation: Boolean = true,
    val rotationDegrees: List<Float>?,
)
