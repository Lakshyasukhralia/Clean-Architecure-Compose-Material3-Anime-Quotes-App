package com.sukhralia.animequotes.presentation

sealed class QuotesListEvent {
    object Refresh : QuotesListEvent()
}
