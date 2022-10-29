package com.example.dictionaryappcompose.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryappcompose.domain.repository.WordInfoRepository
import com.example.dictionaryappcompose.util.Resource
import com.example.dictionaryappcompose.util.UiEvent
import com.example.dictionaryappcompose.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val repository: WordInfoRepository
) : ViewModel() {

    var state by mutableStateOf(WordInfoState())
    var searchQuery by mutableStateOf("")

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        searchQuery = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            repository.getWordInfo(query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            state = state.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )

                        }
                        is Resource.Loading -> {
                            state = state.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UiEvent.ShowSnackBar(
                                    message = result.message ?: UiText.unknownError()
                                )
                            )
                        }
                    }
                }
        }
    }
}