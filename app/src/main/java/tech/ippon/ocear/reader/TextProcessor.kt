package tech.ippon.ocear.reader

import com.google.mlkit.vision.text.Text
import timber.log.Timber

class TextProcessor {
    fun processText(text: Text) {
        Timber.e("text is : ${text.text}\n")
        val result = text.text.split('.')
        Timber.e("$result")
    }
}