package com.example.dictionaryappcompose.data.remote.dto

data class WordInfoDto(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
)