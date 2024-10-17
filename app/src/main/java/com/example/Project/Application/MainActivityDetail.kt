package com.example.Project.Application

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import coil.load
import com.example.Project.*
import com.example.Project.databinding.ActivityMainDetailBinding
import com.example.Project.shared_component.data.Constants
import com.example.Project.utils.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityDetail : AppCompatActivity() {


    //region Properties
    private lateinit var binding1: ActivityMainDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var likedMoviesPreferences: SharedPreferences
    private var movieId: Int = -1
    private var colorPurple: Int = 0
    private var colorBlack: Int = 0

    //endregion

    //region life cycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         colorPurple = ContextCompat.getColor(this, R.color.purple_200)
         colorBlack =
            ContextCompat.getColor(this, R.color.black)
        initialSharedPreferences()
        initialBinding()
        configViewModel()
        callApi()
    }


    //endregion


    //region methods
    private fun initialBinding() {
        binding1 = ActivityMainDetailBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        // Retrieve movie ID from intent extras
        movieId = intent.getIntExtra("MOVIE_ID", -1)

    }

    private fun initialSharedPreferences() {
        likedMoviesPreferences =
            getSharedPreferences(Constants.SHARED_PREFERENCE_KEY_LIKED_MOVIES, MODE_PRIVATE)
    }

    private fun configViewModel() {
        viewModel.movie.observe(this) {

            binding1.title.text = it.title
            binding1.actors.text = it.actors
            binding1.plot.text = it.plot
            binding1.time.text = it.runtime
            binding1.date.text = it.year
            binding1.score.text = it.imdbRating
            binding1.mainImageView.load(it.poster.replaceFirst("https://", "http://"))

            for (resId in it.images) {
                val imageView = ImageView(this)
                val params = LinearLayout.LayoutParams(
                    120, // width in dp
                    120  // height in dp
                )
                params.setMargins(8, 0, 8, 0) // left, top, right, bottom
                imageView.layoutParams = params
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.load(resId)
                binding1.linearLayoutImages.addView(imageView)
            }



            binding1.likeButton.setOnClickListener {
                if (likedMoviesPreferences.containsLikedMovieId(movieId.toString(), Constants.SHARED_PREFERENCE_KEY_LIKED_MOVIES)) {
                    likedMoviesPreferences.removeLikedMovieId(movieId.toString(), Constants.SHARED_PREFERENCE_KEY_LIKED_MOVIES)
                    binding1.likeButton.setColorFilter(colorBlack)
                } else {
                    likedMoviesPreferences.addLikedMovieId(movieId.toString(), Constants.SHARED_PREFERENCE_KEY_LIKED_MOVIES)
                    binding1.likeButton.setColorFilter(colorPurple)
                }
            }

            if (likedMoviesPreferences.containsLikedMovieId(movieId.toString(), Constants.SHARED_PREFERENCE_KEY_LIKED_MOVIES)) {
                binding1.likeButton.setColorFilter(colorPurple)
            } else {
                binding1.likeButton.setColorFilter(colorBlack)
            }



            binding1.imageButton.setOnClickListener {
                finish()
            }


        }
    }

    private fun callApi() {
        viewModel.loadMovieById(movieId)
    }

    //endregion

}