package com.example.dictionaryappcompose.data.remote.dto

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)