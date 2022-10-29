package com.example.dictionaryappcompose.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionaryappcompose.domain.model.WordMeaning

@Entity
data class WordInfoEntity(
    val meanings: List<WordMeaning>,
    val origin: String,
    val phonetic: String,
    val word: String,
    @PrimaryKey val id: Int? = null
)
