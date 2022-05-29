package com.sukhralia.animequotes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val character: String,
    val quote: String
)