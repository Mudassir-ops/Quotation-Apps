package com.example.quotesapp.ui.fragments.quotesFragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.databinding.QuotesItemBinding
import com.example.quotesapp.ui.json.Quotes
import java.util.Locale

class QuotesAdapter(private var quotes: ArrayList<Quotes>,  val context: Context):RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    private var textToSpeech: TextToSpeech? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = QuotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,this)
    }

    override fun getItemCount(): Int = quotes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = quotes[position]
        holder.binding.txtQuotes.text = dataModel.text
        holder.binding.txtQuotesAuthor.text = dataModel.author
        holder.bind(dataModel,context)

    }


    class ViewHolder(val binding: QuotesItemBinding, private val adapter: QuotesAdapter): RecyclerView.ViewHolder(binding.root) {

        fun bind(quotes: Quotes, context: Context) {

            // Copy to clipboard
            binding.icCopy.setOnClickListener {
                val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Quote", quotes.text)
                clipboardManager.setPrimaryClip(clip)
                Toast.makeText(context, "Text copied!", Toast.LENGTH_SHORT).show()
            }

            // Share text
            binding.icShare.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, quotes.text)
                context.startActivity(Intent.createChooser(shareIntent, "Share via"))
            }

            // Text-to-speech
            binding.icVoice.setOnClickListener {
                // Stop any existing speech
                adapter.stopCurrentSpeech()

                // Initialize new TextToSpeech instance
                adapter.textToSpeech = TextToSpeech(context) { status ->
                    if (status != TextToSpeech.ERROR) {
                        adapter.textToSpeech?.language = Locale.US
                        adapter.textToSpeech?.speak(quotes.text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                }
            }
        }
    }

    fun stopCurrentSpeech() {
        // Cancel the current TextToSpeech if it's speaking
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        textToSpeech = null
    }
}