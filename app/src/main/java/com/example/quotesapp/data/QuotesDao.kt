package com.example.quotesapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotesData(quotesEntity: QuotesEntity)

    @Query("DELETE FROM quotes_table WHERE quotesText = :quoteText")
    suspend fun deleteQuoteByText(quoteText: String)

    @Query("SELECT * FROM quotes_table WHERE isFavourite = 1")
    suspend fun getAllFavouriteQuotes(): List<QuotesEntity>
}