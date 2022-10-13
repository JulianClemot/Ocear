package tech.ippon.ocear.common.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.ippon.rakekotlin.KeywordsExtractor

@Module
@InstallIn(SingletonComponent::class)
object RakeModule {
    @Provides
    fun providesRakeKeywordExtractor() = KeywordsExtractor()
}