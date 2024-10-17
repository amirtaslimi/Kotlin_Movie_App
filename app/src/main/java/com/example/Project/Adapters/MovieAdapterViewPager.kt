package com.example.Project.Adapters
import android.annotation.SuppressLint
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.Project.MovieItem
import com.example.Project.databinding.ItemMovieViewPagerBinding

class MovieAdapterViewPager(
    private val movies: List<MovieItem>,
    private val onItemClick: (MovieItem) -> Unit
) : RecyclerView.Adapter<MovieAdapterViewPager.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMovieViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            itemView.setOnClickListener {
                onItemClick(movies[adapterPosition])
            }
        }
        @SuppressLint("SetTextI18n")
        fun bindData(movie: MovieItem) {
            binding.apply {
                textView1.text = movie.title
                textView1.setTypeface(null, Typeface.BOLD)
                textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                textView2.text = movie.imdbRating
                textView3.text = movie.country
                textView4.text = movie.year
                image.load(movie.poster.replaceFirst("https://", "http://"))
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(movies[position])
    }
}