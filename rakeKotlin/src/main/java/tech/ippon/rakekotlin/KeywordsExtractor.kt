package tech.ippon.rakekotlin

import java.util.*
import kotlin.math.max


class KeywordsExtractor(stopWords: List<String> = stopWordsFr) {
    private val stopWordsRegex: Regex by lazy {
        stopWords.joinToString(separator = STOP_WORDS_DELIMITER) { "\\b$it(?![\\w-])" }.toRegex()
    }

    fun extract(content: String): Map<String, Double> {
        val sentences: List<String> = getSentences(content)
        val keywords: List<String> = getKeywords(sentences)

        val wordScores: Map<String, Double> = calculateWordScores(keywords)
        val keywordsCandidates: Map<String, Double> =
            getCandidateKeywordScores(keywords, wordScores)

        return keywordsCandidates.toList().sortedByDescending { it.second }.toMap()
    }

    private fun getSentences(content: String): List<String> =
        content.split(SENTENCES_SPLITTER_REGEX.toRegex()).filter { it.isNotBlank() }

    private fun getKeywords(sentences: List<String>): List<String> =
        sentences
            .asSequence()
            .map { it.trim().replace(stopWordsRegex, STOP_WORDS_DELIMITER) }
            .map { it.split("\\$STOP_WORDS_DELIMITER".toRegex()) }
            .map {
                it.map { phrase ->
                    phrase.trim().lowercase(Locale.getDefault())
                }
            }
            .flatten()
            .filter { it.isNotBlank() }
            .toList()

    private fun calculateWordScores(phrases: List<String>): Map<String, Double> {
        val wordFrequencies = mutableMapOf<String, Int>()
        val wordDegrees = mutableMapOf<String, Int>()
        val wordScores = mutableMapOf<String, Double>()

        phrases.forEach {
            val words = separateWords(it)
            val degree = max(MINIMUM_DEGREE_SCORE, words.size - 1)

            words.forEach { word ->
                wordFrequencies[word] = wordFrequencies.getOrDefault(word, 0) + 1
                wordDegrees[word] = wordDegrees.getOrDefault(word, 0) + degree
            }
        }

        wordFrequencies.keys.forEach { frequenciesKey ->
            wordScores[frequenciesKey] =
                wordDegrees.getOrDefault(
                    frequenciesKey,
                    0
                ).toDouble() / wordFrequencies.getOrDefault(
                    frequenciesKey,
                    MINIMUM_DEGREE_SCORE
                ).toDouble()
        }
        return wordScores
    }

    private fun getCandidateKeywordScores(
        keywords: List<String>,
        wordScores: Map<String, Double>
    ): Map<String, Double> = keywords.associateWith {
        separateWords(it).sumOf { word -> wordScores.getOrDefault(word, 0.0) }
    }

    private fun separateWords(sentences: String) =
        sentences
            .split(WORDS_SPLITTER_REGEX.toRegex())
            .map { it.trim().lowercase(Locale.getDefault()) }
            .filter { it.isNotEmpty() }

    companion object {
        private const val SENTENCES_SPLITTER_REGEX =
            "[.!?,;:\\t\\\\\"\\\\(\\\\)\\u2019\\u2013]|\\\\s\\\\-\\\\s"
        private const val WORDS_SPLITTER_REGEX = "(\\s|[.;:,])+"

        private const val STOP_WORDS_DELIMITER = "|"
        private const val MINIMUM_DEGREE_SCORE = 1
    }
}