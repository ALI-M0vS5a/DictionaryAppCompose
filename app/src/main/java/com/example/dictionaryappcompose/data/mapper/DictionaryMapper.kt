package com.example.dictionaryappcompose.data.mapper

import com.example.dictionaryappcompose.data.local.entity.WordInfoEntity
import com.example.dictionaryappcompose.data.remote.dto.Definition
import com.example.dictionaryappcompose.data.remote.dto.Meaning
import com.example.dictionaryappcompose.data.remote.dto.WordInfoDto
import com.example.dictionaryappcompose.domain.model.WordDefinition
import com.example.dictionaryappcompose.domain.model.WordInfo
import com.example.dictionaryappcompose.domain.model.WordMeaning

fun Definition.toWordDefinition(): WordDefinition {
    return WordDefinition(
        antonyms = antonyms,
        definition = definition,
        example = example,
        synonyms = synonyms
    )
}

fun Meaning.toWordMeaning(): WordMeaning {
    return WordMeaning(
        definitions = definitions.map { it.toWordDefinition() },
        partOfSpeech = partOfSpeech
    )
}

fun WordInfoDto.toWordInfo(): WordInfo {
    return WordInfo(
        meanings = meanings.map { it.toWordMeaning() },
        origin = origin,
        phonetic = phonetic,
        word = word
    )
}

fun WordInfoEntity.toWordInfo(): WordInfo {
    return WordInfo(
        meanings = meanings,
        origin = origin,
        phonetic = phonetic,
        word = word
    )
}

fun WordInfoDto.toWordInfoEntity(): WordInfoEntity {
    return WordInfoEntity(
        meanings = meanings.map { it.toWordMeaning() },
        origin = origin,
        phonetic = phonetic,
        word = word
    )
}