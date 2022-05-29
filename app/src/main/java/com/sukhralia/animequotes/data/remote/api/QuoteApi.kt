package com.sukhralia.animequotes.data.remote.api

import com.sukhralia.animequotes.data.remote.dto.QuoteDto
import retrofit2.http.GET

interface QuoteApi {

    @GET("/api/quotes")
    suspend fun getQuotesList(): List<QuoteDto>

    companion object {

        const val BASE_URL = "https://animechan.vercel.app"

    }
}
