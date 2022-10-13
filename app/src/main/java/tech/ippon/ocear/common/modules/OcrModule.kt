package tech.ippon.ocear.common.modules

import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OcrModule {
    @Singleton
    @Provides
    fun providesMLKitOcrExtractor() =
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
}