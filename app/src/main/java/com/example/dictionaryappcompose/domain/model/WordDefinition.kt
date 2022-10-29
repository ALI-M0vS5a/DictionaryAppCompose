package com.example.dictionaryappcompose.domain.model


data class WordDefinition(
    val antonyms: List<Any>,
    val definition: String,
    val example: String?,
    val synonyms: List<Any>
)
