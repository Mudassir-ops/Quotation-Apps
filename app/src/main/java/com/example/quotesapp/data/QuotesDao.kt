package com.example.quotesapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotesData(quotesEntity: QuotesEntity)

    @Query("DELETE FROM quotes_table WHERE quotesText = :quoteText")
    suspend fun deleteQuoteByText(quoteText: String)

    @Query("SELECT * FROM quotes_table WHERE isFavourite = 1")
    fun getAllFavouriteQuotesFlow(): Flow<List<QuotesEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM quotes_table WHERE quotesText = :text AND authorName = :author AND isFavourite = 1)")
    suspend fun isFavorite(text: String, author: String): Boolean
}