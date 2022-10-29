package com.example.dictionaryappcompose.util


sealed class UiEvent {
    data class ShowSnackBar(val message: UiText): UiEvent()
}
