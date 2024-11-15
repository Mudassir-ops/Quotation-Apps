package com.example.quotesapp.ui.fragments.favouriteFragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.data.QuotesEntity
import com.example.quotesapp.databinding.FavouriteItemBinding
import com.example.quotesapp.ui.fragments.quotesFragment.QuotesAdapter
import com.example.quotesapp.ui.json.Quotes
import java.util.Locale

class FavouriteAdapter(private val favList: List<QuotesEntity>,val context: Context,) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    private var textToSpeech: TextToSpeech? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,this)
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = favList[position]
        holder.binding.txtFavouriteQuotes.text = quote.quotesText
        holder.binding.txtFavouriteQuotesAuthor.text = quote.authorName
        holder.bind(quote, context)
    }

    class ViewHolder(val binding: FavouriteItemBinding,private val adapter: FavouriteAdapter) : RecyclerView.ViewHolder(binding.root){
        fun bind(quotes: QuotesEntity, context: Context) {

            binding.icCopy.setOnClickListener {
                val clipboardManager =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Quote", quotes.quotesText)
                clipboardManager.setPrimaryClip(clip)
                Toast.makeText(context, "Text copied!", Toast.LENGTH_SHORT).show()
            }

            binding.icShare.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, quotes.quotesText)
                context.startActivity(Intent.createChooser(shareIntent, "Share via"))
            }

            binding.icVoice.setOnClickListener {
                adapter.stopCurrentSpeech()

                adapter.textToSpeech = TextToSpeech(context) { status ->
                    if (status != TextToSpeech.ERROR) {
                        adapter.textToSpeech?.language = Locale.US
                        adapter.textToSpeech?.speak(
                            quotes.quotesText,
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
}