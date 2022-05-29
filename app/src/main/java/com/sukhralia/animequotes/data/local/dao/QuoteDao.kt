package com.sukhralia.animequotes.data.local.dao

import androidx.room.*
import com.sukhralia.animequotes.data.local.entity.QuoteEntity

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotesList(quotes:List<QuoteEntity>)

    @Query("DELETE FROM QuoteEntity")
    suspend fun deleteQuoteList()

    @Query("SELECT * FROM QuoteEntity")
    suspend fun getStoredQuotesList():List<QuoteEntity>
}