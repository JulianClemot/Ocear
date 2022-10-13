package tech.ippon.ocear.reader

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tech.ippon.ocear.common.ArKeyword
import tech.ippon.ocear.reader.data.YakeRepository
import tech.ippon.rakekotlin.KeywordsExtractor
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val recognizer: TextRecognizer,
    private val keywordsExtractor: KeywordsExtractor,
    private val yakeRepository: YakeRepository,
) : ViewModel() {

    var chosenColor: Color = Color.Black

    fun onImageTaken(goToArScreen: (keywords: List<ArKeyword>, chosenColor: Color) -> Unit): (InputImage) -> Unit =
        { inputImage ->
            try {
                recognizer.process(inputImage).addOnSuccessListener {
                    getKeywords(it.text, goToArScreen)
                }.addOnFailureListener { exception ->
                    //show error
                    exception.printStackTrace()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    private fun getKeywords(
        textDetected: String,
        goToArScreen: (keywords: List<ArKeyword>, chosenColor: Color) -> Unit
    ) {
        viewModelScope.launch {
            try {
                yakeRepository.getYakeKeywords(textDetected, 2, 20)
                    .collect { result ->
                        val arKeywords =
                            buildArKeywordsFromYake(result.keywords.associate { it.label to it.score })
                        goToArScreen(arKeywords, chosenColor)
                    }
            } catch (exception: Exception) {
                goToArScreen(
                    buildArKeywordsFromRake(keywordsExtractor.extract(textDetected)),
                    chosenColor
                )
                exception.printStackTrace()
            }
        }
    }

    private fun buildArKeywordsFromYake(extractedKeywords: Map<String, Double>) =
        extractedKeywords
            .mapNotNull { extractedKeywordEntry ->
                ArKeyword.values()
                    .firstOrNull { arKeyword ->
                        arKeyword.synonyms.any {
                            extractedKeywordEntry.key.contains(it, ignoreCase = true)
                        }
                    }
            }.distinct()

    private fun buildArKeywordsFromRake(extractedKeywords: Map<String, Double>) =
        extractedKeywords
            .filter { it.value > 1 }
            .mapNotNull { extractedKeywordEntry ->
                ArKeyword.values()
                    .firstOrNull { arKeyword ->
                        arKeyword.synonyms.any {
                            extractedKeywordEntry.key.contains(it, ignoreCase = true)
                        }
                    }
            }.distinct()
}