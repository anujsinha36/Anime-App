package com.example.jikan.ui.activities

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jikan.api.RetrofitBuilder
import com.example.jikan.data.repository.AnimeRepository
import com.example.jikan.databinding.ActivityAnimeDetailsBinding
import com.example.jikan.ui.viewmodels.AnimeViewModel
import com.example.jikan.ui.viewmodels.AnimeViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class AnimeDetailsScreen : AppCompatActivity() {
    private lateinit var viewModel: AnimeViewModel
    private lateinit var repository: AnimeRepository
    private lateinit var viewModelFactory: AnimeViewModelFactory
    private lateinit var binding: ActivityAnimeDetailsBinding
    private lateinit var youTubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genreList = mutableListOf<String>()
        youTubePlayerView = binding.youtubePlayer
        lifecycle.addObserver(youTubePlayerView)

        // anime ID from the click in the first page
        val animeId = intent.getIntExtra("animeId", -1)

        repository = AnimeRepository(RetrofitBuilder.getRetrofitInstance())
        viewModelFactory = AnimeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AnimeViewModel::class.java]


        //Observe the anime details
        viewModel.animeDetailsLiveData.observe(this) {
            if (it.data.title_english != null){
                binding.animeCapturedTitle.text = it.data.title_english
            }
            else{
                binding.animeCapturedTitle.text = it.data.title
            }

            val synopsis = "Synopsis: ${it.data.synopsis}"
            setBoldText(binding.animeSynopsis, synopsis, "Synopsis")

            genreList.addAll(it.data.genres.map { genre -> genre.name })
            val animeGenre = "Genre: ${genreList.joinToString(", ")}"
            setBoldText(binding.animeGenre, animeGenre, "Genre")

            val animeStatus = "Status: ${it.data.status}"
            setBoldText(binding.animeStatus, animeStatus, "Status")

            val animeEpisodeCount = "Episodes: ${it.data.episodes}"
            setBoldText(binding.animeEpisodeCount, animeEpisodeCount, "Episodes")

            val animeRatingsByViewers = "Rating: ${it.data.score}"
            setBoldText(binding.animeRatingsByViewers, animeRatingsByViewers, "Rating")

            val trailerUrl = it.data.trailer.youtube_id
            if (trailerUrl != null){
                binding.youtubePlayer.visibility = View.VISIBLE
                binding.posterImage.visibility = View.GONE
                playTrailer(trailerUrl)
            }
            else{
                binding.youtubePlayer.visibility = View.GONE
                binding.posterImage.visibility = View.VISIBLE
                Glide.with(this)
                    .load(it.data.images.jpg.image_url)
                    .into(binding.posterImage)
            }
        }
        viewModel.getAnimeDetails(animeId)
    }

    //function to play trailer
    private fun playTrailer(url: String) {
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                //Prevents flickering by using the GPU hard acceleration
                youTubePlayerView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
                youTubePlayer.loadVideo(url, 0f)
            }
        })
    }

    //function to make Prefix text bold
    private fun setBoldText(textView: TextView, fullText: String, boldText: String) {
        val spannableString = SpannableString(fullText)

        // Find the start and end indexes of the bold text
        val startIndex = fullText.indexOf(boldText)
        if (startIndex != -1) { // Ensure the boldText exists in fullText
            val endIndex = startIndex + boldText.length
            spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textView.text = spannableString
    }

    public override fun onDestroy() {
        super.onDestroy()
        youTubePlayerView.release() //releasing player once activity is destroyed
    }
}