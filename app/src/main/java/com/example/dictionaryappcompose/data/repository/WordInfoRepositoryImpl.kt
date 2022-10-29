package com.example.dictionaryappcompose.data.repository

import com.example.dictionaryappcompose.R
import com.example.dictionaryappcompose.data.local.WordInfoDao
import com.example.dictionaryappcompose.data.mapper.toWordInfo
import com.example.dictionaryappcompose.data.mapper.toWordInfoEntity
import com.example.dictionaryappcompose.data.remote.DictionaryApi
import com.example.dictionaryappcompose.domain.model.WordInfo
import com.example.dictionaryappcompose.domain.repository.WordInfoRepository
import com.example.dictionaryappcompose.util.Resource
import com.example.dictionaryappcompose.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {
    override suspend fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> {
        return flow {
            emit(Resource.Loading())
            val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
            emit(Resource.Loading(data = wordInfos))
            if(word.isBlank()){
                return@flow
            }
            val remote = try {
                api.getWordInfo(word)
            }  catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = UiText.StringResource(
                            resId = R.string.please_check_your_connection
                        ),
                        data = wordInfos
                    )
                )
                null
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = (e.localizedMessage ?: UiText.StringResource(
                            resId = R.string.Oops_something_went_wrong
                        )) as UiText,
                        data = wordInfos
                    )
                )
                null
            }
            remote?.let { wordInfo ->
                dao.deleteWordInfos(wordInfo.map { it.word })
                dao.insertMultipleWords(wordInfo.map { it.toWordInfoEntity() })
                emit(Resource.Success(
                    data = dao.getWordInfos(word).map { it.toWordInfo() }
                ))
                emit(Resource.Loading())
            }
        }
    }
}