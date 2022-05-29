package com.sukhralia.animequotes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sukhralia.animequotes.data.local.dao.QuoteDao
import com.sukhralia.animequotes.data.local.entity.QuoteEntity


@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    abstract val dao: QuoteDao

}
