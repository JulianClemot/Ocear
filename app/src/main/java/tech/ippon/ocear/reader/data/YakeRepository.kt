package tech.ippon.ocear.reader.data

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class YakeRepository @Inject constructor(
    private val yakeApi: YakeApi,
    private val dispatcher: CoroutineContext
) {

    suspend fun getYakeKeywords(content: String, maxNGramSize: Int, maxKeywordNumber: Int) =
        withContext(dispatcher) {
            flowOf(
                yakeApi.getKeywords(
                    content,
                    maxNGramSize,
                    maxKeywordNumber,
                )
            )
        }
}