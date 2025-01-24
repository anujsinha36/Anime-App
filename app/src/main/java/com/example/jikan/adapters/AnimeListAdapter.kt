package com.example.jikan.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jikan.R
import com.example.jikan.data.models.TopAnime.Data
import com.example.jikan.databinding.CustomRvLayoutBinding
import com.example.jikan.ui.activities.AnimeDetailsScreen

class AnimeListAdapter(
    private val animeList: List<Data>
) : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {

    class AnimeViewHolder(
        private val binding: CustomRvLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.animeTitle
        val episodes = binding.animeEpisodes
        val rating = binding.animeRating
        val poster = binding.animePoster

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): AnimeListAdapter.AnimeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_rv_layout, parent, false)
        return AnimeViewHolder(CustomRvLayoutBinding.bind(view))
    }

    override fun onBindViewHolder(holder: AnimeListAdapter.AnimeViewHolder, position: Int) {
        var currentItem = animeList[position]
        if (currentItem.title_english != null){
            holder.title.text = "${currentItem.title_english}"
        }
        else{
            holder.title.text = "${currentItem.title}"
        }
        holder.episodes.text = "Episode(s): ${currentItem.episodes}"
        holder.rating.text = "Rating: ${currentItem.score}"


        Glide.with(holder.itemView).load(currentItem.images.jpg.image_url).into(holder.poster)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AnimeDetailsScreen::class.java)
            intent.putExtra("animeId", currentItem.mal_id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}