package com.sukhralia.animequotes.data.mapper

import com.sukhralia.animequotes.data.local.entity.QuoteEntity
import com.sukhralia.animequotes.data.remote.dto.QuoteDto
import com.sukhralia.animequotes.domain.model.Quote

fun QuoteEntity.toQuote():Quote{
    return Quote(
        quote = quote,
        character = character
    )
}

fun Quote.toQuoteEntity():QuoteEntity{
    return QuoteEntity(
        quote = quote,
        character = character
    )
}

fun QuoteDto.toQuote():Quote{
    return Quote(
        quote = quote,
        character = character
    )
}