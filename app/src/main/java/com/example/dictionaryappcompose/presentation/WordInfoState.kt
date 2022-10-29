package com.example.dictionaryappcompose.presentation

import com.example.dictionaryappcompose.domain.model.WordInfo


data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
