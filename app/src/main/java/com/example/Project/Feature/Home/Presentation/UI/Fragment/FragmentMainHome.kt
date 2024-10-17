package com.example.Project.Feature.Home.Presentation.UI.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.example.Project.Adapters.MovieAdapter
import com.example.Project.Adapters.MovieAdapterViewPager
import com.example.Project.Adapters.GenresAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.Project.*
import com.example.Project.Application.MainActivityDetail
import com.example.Project.Feature.Home.Presentation.ViewModel.HomeViewModel
import com.example.Project.databinding.FragmentMainHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMainHome : Fragment() {

    //region properties
    private lateinit var binding: FragmentMainHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())
    private val updateRunnable = object : Runnable {
        override fun run() {
            val nextItem = (binding.movieViewPager.currentItem + 1) % binding.movieViewPager.adapter!!.itemCount
            binding.movieViewPager.setCurrentItem(nextItem, true)
            handler.postDelayed(this, 3000)
        }
    }
    //endregion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainHomeBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewModel()
        callApi()
    }


    private fun configViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            binding.movieRecyclerView.adapter = MovieAdapter(movies) {clickedHouse ->
                navigateToDetails(clickedHouse)
            }
            binding.movieRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            binding.movieViewPager.adapter = MovieAdapterViewPager(movies) {clickedHouse ->
                navigateToDetails(clickedHouse)
            }
            dotIndicator(binding.movieViewPager)
            handler.postDelayed(updateRunnable, 1000)
        }

        viewModel.genres.observe(viewLifecycleOwner){genres ->
            binding.genresRecyclerView.adapter = GenresAdapter(genres){clickedGenre ->
                viewModel.genreId = clickedGenre.id
                viewModel.loadMovies()
            }
            binding.genresRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }


    private fun dotIndicator(movieViewPager: ViewPager2) {
        val dotsCount = movieViewPager.adapter!!.itemCount
        val dots = arrayOfNulls<ImageView>(dotsCount)

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(requireContext())
            dots[i]?.setImageResource(R.drawable.baseline_circle_24_white)

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 0, 4, 0)
            binding.indicatorLayout.addView(dots[i], params)
        }

        dots[0]?.setImageResource(R.drawable.baseline_circle_24_black)

        movieViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                for (i in 0 until dotsCount) {
                    dots[i]?.setImageResource(R.drawable.baseline_circle_24_white)
                }
                dots[position]?.setImageResource(R.drawable.baseline_circle_24_black)
            }
        })
    }

    private fun navigateToDetails(clickedMovie: MovieItem) {

        val intent = Intent(requireContext(), MainActivityDetail::class.java).apply {
            putExtra("MOVIE_ID", clickedMovie.id)
        }
        startActivity(intent)
    }

    private fun callApi() {
        viewModel.loadMovies()
        viewModel.loadGenres()
    }

}