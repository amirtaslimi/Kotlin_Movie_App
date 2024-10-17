package com.example.Project.Adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Project.GenreItem
import com.example.Project.databinding.ItemTagsBinding

class GenresAdapter(
    private val genres: List<GenreItem>,
    private val onItemClick: (GenreItem) -> Unit
) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTagsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick(genres[adapterPosition])
            }
        }


        @SuppressLint("SetTextI18n")
        fun bindData(genre: GenreItem) {
            binding.apply {
                textview.text = genre.name
                //textview.setTypeface(null, Typeface.BOLD)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTagsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = genres.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(genres[position])
    }

}