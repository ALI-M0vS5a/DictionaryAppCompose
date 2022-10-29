package com.example.dictionaryappcompose.domain.model


data class WordMeaning(
    val definitions: List<WordDefinition>,
    val partOfSpeech: String
)
