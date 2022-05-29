package com.sukhralia.animequotes.domain.repository

import com.sukhralia.animequotes.domain.model.Quote
import com.sukhralia.animequotes.util.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    suspend fun getQuotesList(fetchFromRemote: Boolean): Flow<Resource<List<Quote>>>

}