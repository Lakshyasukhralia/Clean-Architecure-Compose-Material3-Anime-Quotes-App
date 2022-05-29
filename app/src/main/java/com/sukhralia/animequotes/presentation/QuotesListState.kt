package com.sukhralia.animequotes.presentation

import com.sukhralia.animequotes.domain.model.Quote

data class QuotesListState(
    val quotes: List<Quote> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = ""
)
