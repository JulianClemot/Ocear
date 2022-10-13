package tech.ippon.ocear.reader.data

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query
import tech.ippon.ocear.reader.domain.YakeKeywordsResult

interface YakeApi {
    @FormUrlEncoded
    @POST("yake/v2/extract_keywords")
    suspend fun getKeywords(
        @Field("content") content: String,
        @Query("max_ngram_size") maxNGramsSize: Int,
        @Query("number_of_keywords") maxKeywordsNumber: Int
    ): YakeKeywordsResult
}