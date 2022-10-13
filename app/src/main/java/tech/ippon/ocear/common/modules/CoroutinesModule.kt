package tech.ippon.ocear.common.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {
    @Singleton
    @Provides
    fun providesDispatcher(): CoroutineContext = Dispatchers.IO
}