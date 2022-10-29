package com.example.dictionaryappcompose.domain.repository

import com.example.dictionaryappcompose.domain.model.WordInfo
import com.example.dictionaryappcompose.util.Resource
import kotlinx.coroutines.flow.Flow


interface WordInfoRepository {
    suspend fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}