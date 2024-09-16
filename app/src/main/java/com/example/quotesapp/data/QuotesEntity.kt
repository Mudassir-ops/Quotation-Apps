package com.example.quotesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes_table")
data class QuotesEntity(
    @PrimaryKey(autoGenerate = false)
    val quotesText:String = "",
    val authorName :String = ""
)