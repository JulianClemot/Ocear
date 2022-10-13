package tech.ippon.ocear.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flowOf
import tech.ippon.ocear.R
import tech.ippon.ocear.book_details.models.Book
import tech.ippon.ocear.ui.theme.earthToMoonColors
import tech.ippon.ocear.ui.theme.journeyToTheCenterOfEarthColors
import tech.ippon.ocear.ui.theme.leaguesUnderTheSeaColors

class HomeViewModel : ViewModel() {

    val books = listOf(
            Book(
                title = "20 000 lieues sous les mers",
                shortDescription = "Un monstre marin, « une chose énorme », ayant été signalé par plusieurs navires à travers le monde, une expédition est organisée sur l’Abraham Lincoln, frégate américaine, pour purger les mers de ce monstre inquiétant. A bord se trouvent le Français Pierre Aronnax, professeur au Muséum de Paris, et Conseil, son fidèle domestique.\n" +
                        "Une fois parvenus en vue du monstre, deux immenses trombes d’eau s’abattent sur le pont de la frégate, précipitant Aronnax, Conseil et le harponneur canadien Ned Land sur le dos du monstre… qui se révèle être un fabuleux sous-marin, le Nautilus, conçu et commandé par un étrange personnage, le capitaine Nemo, qui paraît farouchement hostile à toute l’humanité !\n" +
                        "Condamnés à ne plus jamais revoir leur patrie, leurs parents, leurs amis, la plus extraordinaire aventure commence pourtant pour les trois hommes...",
                author = "Jules Verne",
                coverResource = R.drawable.under_the_sea,
            ),
            Book(
                title = "De la terre à la lune",
                shortDescription = "A la fin de la guerre fédérale des états-Unis, les fanatiques artilleurs du Gun-Club (Club-Canon) de Baltimore sont bien désoeuvrés. Un beau jour, le président, Impey Barbicane, leur fait une proposition qui, le premier moment de stupeur passé, est accueillie avec un enthousiasme délirant. Il s'agit de se mettre en communication avec la Lune en lui envoyant un boulet, un énorme projectile qui serait lancé par un gigantesque canon ! Tandis que ce projet inouï est en voie d'exécution, un Parisien, Michel Ardan, un de ces originaux que le Créateur invente dans un moment de fantaisie, et dont il brise aussitôt le moule, télégraphie à Barbicane : « Remplacez obus sphérique par projectile cylindroconique. Partirai dedans »...",
                author = "Jules Verne",
                coverResource = R.drawable.earth_to_moon,
            ),
            Book(
                title = "Voyage au centre de la terre",
                shortDescription = "Le 24 mai 1863, le célèbre géologue Lidenbrock découvre un mystérieux manuscrit qui va l'entraîner, lui et son neveu Axel, dans une extraordinaire aventure, à l’intérieur d’un volcan islandais, jusqu’au centre de la Terre.",
                author = "Jules Verne",
                coverResource = R.drawable.inside_earth,
            ),
        )

    val colors = listOf(leaguesUnderTheSeaColors, earthToMoonColors, journeyToTheCenterOfEarthColors)
}