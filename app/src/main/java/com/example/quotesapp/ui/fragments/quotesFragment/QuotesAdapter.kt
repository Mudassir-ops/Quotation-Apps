package com.example.quotesapp.ui.fragments.quotesFragment

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.data.QuotesDao
import com.example.quotesapp.data.QuotesEntity
import com.example.quotesapp.databinding.QuotesItemBinding
import com.example.quotesapp.ui.json.Quotes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class QuotesAdapter(
    var quotes: ArrayList<Quotes>,
    private val quotesDao: QuotesDao,
    val context: Context,
) :
    RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    private var textToSpeech: TextToSpeech? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = QuotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, this)
    }

    override fun getItemCount(): Int = quotes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = quotes[position]
        holder.binding.txtQuotes.text = dataModel.text
        holder.binding.txtQuotesAuthor.text = dataModel.author
        holder.bind(dataModel, context)
        holder.binding.icFavourite.setOnClickListener {
            saveQuoteToDatabase(dataModel, holder)
        }
        CoroutineScope(Dispatchers.Main).launch {
            val isFavorite = quotesDao.isFavorite(dataModel.text ?: "", dataModel.author ?: "")
            holder.binding.icFavourite.setImageResource(
                if (isFavorite) R.drawable.ic_red_fav else R.drawable.ic_favourite
            )
        }
    }


    class ViewHolder(val binding: QuotesItemBinding, private val adapter: QuotesAdapter) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quotes: Quotes, context: Context) {

            binding.icCopy.setOnClickListener {
                val clipboardManager =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Quote", quotes.text)
                clipboardManager.setPrimaryClip(clip)
                Toast.makeText(context, "Text copied!", Toast.LENGTH_SHORT).show()
            }

            binding.icShare.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, quotes.text)
                context.startActivity(Intent.createChooser(shareIntent, "Share via"))
            }

            binding.icVoice.setOnClickListener {
                adapter.stopCurrentSpeech()

                adapter.textToSpeech = TextToSpeech(context) { status ->
                    if (status != TextToSpeech.ERROR) {
                        adapter.textToSpeech?.language = Locale.US
                        adapter.textToSpeech?.speak(
                            quotes.text,
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            null
                        )
                    }
                }
            }
        }
    }

    fun stopCurrentSpeech() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        textToSpeech = null
    }

    /*@SuppressLint("NotifyDataSetChanged")
    private fun saveQuoteToDatabase(quote: Quotes, holder: ViewHolder) {
        CoroutineScope(Dispatchers.IO).launch {
            val quoteEntity = QuotesEntity(
                quotesText = quote.text?:return@launch,
                authorName = quote.author?: return@launch,
                isFavourite = true
            )


            quotesDao.insertQuotesData(quoteEntity)
            CoroutineScope(Dispatchers.Main).launch {
                val isFavorite = quotesDao.isFavorite(quote.text ?: "", quote.author ?: "")
                holder.binding.icFavourite.setImageResource(
                    if (isFavorite) R.drawable.ic_red_fav else R.drawable.ic_favourite
                )
                // Notify the adapter to refresh
                notifyDataSetChanged()
            }


        }
    }*/

    @SuppressLint("NotifyDataSetChanged")
    private fun saveQuoteToDatabase(quote: Quotes, holder: ViewHolder) {
        CoroutineScope(Dispatchers.IO).launch {
            val isFavorite = quotesDao.isFavorite(quote.text ?: return@launch, quote.author ?: return@launch)

            if (isFavorite) {
                // If the quote is already a favorite, delete it
                quotesDao.deleteQuoteByText(quote.text ?: return@launch)
            } else {
                // Otherwise, save it as a favorite
                val quoteEntity = QuotesEntity(
                    quotesText = quote.text ?: return@launch,
                    authorName = quote.author ?: return@launch,
                    isFavourite = true
                )
                quotesDao.insertQuotesData(quoteEntity)
            }

            CoroutineScope(Dispatchers.Main).launch {
                // Update the icon based on whether it's a favorite or not
                holder.binding.icFavourite.setImageResource(
                    if (isFavorite) R.drawable.ic_favourite else R.drawable.ic_red_fav
                )
                notifyDataSetChanged() // Refresh the adapter
            }
        }
    }

}