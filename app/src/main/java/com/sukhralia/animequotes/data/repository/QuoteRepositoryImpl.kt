package com.sukhralia.animequotes.data.repository

import com.sukhralia.animequotes.data.local.database.QuoteDatabase
import com.sukhralia.animequotes.data.mapper.toQuote
import com.sukhralia.animequotes.data.mapper.toQuoteEntity
import com.sukhralia.animequotes.data.remote.api.QuoteApi
import com.sukhralia.animequotes.domain.model.Quote
import com.sukhralia.animequotes.domain.repository.QuoteRepository
import com.sukhralia.animequotes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    private val api: QuoteApi,
    private val db: QuoteDatabase
) : QuoteRepository {

    private val dao = db.dao

    override suspend fun getQuotesList(fetchFromRemote: Boolean): Flow<Resource<List<Quote>>> {

        return flow {

            emit(Resource.Loading(true))
            val localQuotesList = dao.getStoredQuotesList()

            val isDbEmpty = localQuotesList.isEmpty()
            val loadFromDb = !isDbEmpty && !fetchFromRemote

            if (loadFromDb) {
                emit(Resource.Success(
                    data = localQuotesList.map { it.toQuote() }
                ))
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteQuotesList = try {
                val response = api.getQuotesList()
                response.map { it.toQuote() }
            } catch (e: IOException) {
                emit(Resource.Error(message = "Error = ${e.message}"))
                null
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Error = ${e.message}"))
                null
            }

            remoteQuotesList?.let { quotes ->
                dao.deleteQuoteList()
                dao.insertQuotesList(quotes.map { it.toQuoteEntity() })
                emit(Resource.Success(dao.getStoredQuotesList().map { it.toQuote() }))
                emit(Resource.Loading(false))
            }

        }

    }
}