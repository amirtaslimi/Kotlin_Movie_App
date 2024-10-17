package com.example.Project.Feature.Search.Presentation.UI.Fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Project.Application.MainActivityDetail
import com.example.Project.Feature.Search.Presentation.ViewModel.SearchViewModel
import com.example.Project.Adapters.MovieAdapter
import com.example.Project.MovieItem
import com.example.Project.databinding.FragmentMainFavoriteBinding
import com.example.Project.databinding.FragmentMainHomeBinding
import com.example.Project.databinding.FragmentMainSearchBinding
import com.example.Project.shared_component.data.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMainSearch : Fragment() {
    //region properties
    private lateinit var binding: FragmentMainSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    //endregion

    //region lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = FragmentMainSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearch()
        configViewModel()
        callApi()
    }
    //endregion

    //region methods
    private fun configViewModel() {
        viewModel.filteredMovies.observe(viewLifecycleOwner) { movies ->

            binding.movieRecyclerView.adapter = MovieAdapter(movies) {clickedMovie ->
                navigateToDetails(clickedMovie)
            }
            binding.movieRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }

    private fun navigateToDetails(clickedHouse: MovieItem) {

        val intent = Intent(requireContext(), MainActivityDetail::class.java).apply {
            putExtra("MOVIE_ID", clickedHouse.id)
        }
        startActivity(intent)
    }


    private fun setupSearch() {
        binding.searchEditText.addTextChangedListener { text ->
            viewModel.filterMovies(text.toString())
        }
    }
    private fun callApi() {
        viewModel.loadMovies()
    }
    //endregion
}