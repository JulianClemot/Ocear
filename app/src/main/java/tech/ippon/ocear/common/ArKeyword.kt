package tech.ippon.ocear.common

import android.os.Parcelable
import io.github.sceneview.ar.node.PlacementMode
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import tech.ippon.ocear.ar.Model3d

@Parcelize
@Serializable
enum class ArKeyword(
    val label: String,
    val synonyms: List<String>,
    val placementMode: PlacementMode,
    val scale: Float,
    val fileLocation: String,
    val rotationDegrees: List<Float>? = null,
) : Parcelable {
    NEMO(
        "Nemo",
        listOf(
            "Nemo",
            "Capitaine",
        ),
        placementMode = PlacementMode.PLANE_HORIZONTAL,
        scale = 1f,
        fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
    ),
    NED_LAND(
        "Ned Land",
        listOf(
            "Ned",
            "Land",
            "Harponneur",
            "Canadien",
        ),
        placementMode = PlacementMode.PLANE_HORIZONTAL,
        scale = 1f,
        fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
        rotationDegrees = listOf(270F, 0F, 0F)
    ),
    ARONNAX(
        "Pierre Aronnax",
        listOf(
            "Pierre",
            "Aronnax",
            "professeur",
            "médecin",
            "medecin",
        ),
        placementMode = PlacementMode.PLANE_HORIZONTAL,
        scale = 1f,
        fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
    ),
    CONSEIL(
        "Conseil",
        listOf(
            "Conseil",
            "flamand",
            "domestique",
        ),
        placementMode = PlacementMode.PLANE_HORIZONTAL,
        scale = 1f,
        fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
    ),
    NAUTILUS(
        "Nautilus",
        listOf(
            "nautilus",
            "narval",
            "sous-marin",
            "sous-marine",
            "submersible",
            "monstre marin",
        ),
        placementMode = PlacementMode.PLANE_HORIZONTAL,
        scale = 1f,
        fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
    ),
    BOAT(
        "Bateau",
        listOf(
            "bateau",
            "vaisseau",
            "frégate",
            "navire",
            "barque",
            "Abraham Lincoln"
        ),
        placementMode = PlacementMode.PLANE_HORIZONTAL,
        scale = 1.5f,
        fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
    ),
    SEA(
        "Océan",
        listOf(
            "mer",
            "océan",
            "ocean",
            "atlantique",
            "pacifique",
            "vague",
            "marée",
            "maree",
            "houle",
            "eau"
        ),
        placementMode = PlacementMode.PLANE_HORIZONTAL,
        scale = 1.5f,
        fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
    ),
    EARTH(
        "Terre",
        listOf(
            "terre",
            "earth",
            "planète",
            "planete",
        ),
        placementMode = PlacementMode.PLANE_HORIZONTAL_AND_VERTICAL,
        scale = 1f,
        fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
    ),
    MOON(
        "Lune",
        listOf(
            "lune"
        ),
        placementMode = PlacementMode.PLANE_HORIZONTAL_AND_VERTICAL,
        scale = 1f,
        fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
    );

    fun to3dModel() = Model3d(
        label = this.label,
        fileLocation = this.fileLocation,
        scaleUnits = this.scale,
        placementMode = this.placementMode,
        rotationDegrees = this.rotationDegrees
    )
}