package com.example.dictionaryappcompose.domain.model


data class WordInfo(
    val meanings: List<WordMeaning>,
    val origin: String,
    val phonetic: String,
    val word: String
)
