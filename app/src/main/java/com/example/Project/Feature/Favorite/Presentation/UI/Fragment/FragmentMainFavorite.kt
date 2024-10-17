package com.example.Project.Feature.Favorite.Presentation.UI.Fragment
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Project.Application.MainActivityDetail
import com.example.Project.Feature.Favorite.Presentation.ViewModel.FavoriteViewModel
import com.example.Project.Adapters.MovieAdapter
import com.example.Project.MovieItem
import com.example.Project.databinding.FragmentMainFavoriteBinding
import com.example.Project.shared_component.data.Constants
import com.example.Project.utils.extensions.getStringList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMainFavorite : Fragment() {
    //region properties
    private lateinit var binding: FragmentMainFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
    //endregion

    //region lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentMainFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewModel()
        callApi()
    }
    //endregion

    //region methods
    private fun configViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                binding.movieRecyclerView.adapter = MovieAdapter(movies) { clickedMovie ->
                    navigateToDetails(clickedMovie)
                }
                binding.movieRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
            }?:run{
                binding.movieRecyclerView.visibility = View.GONE
                binding.emptyTextView.visibility = View.VISIBLE
            }

        }
    }

    private fun navigateToDetails(clickedHouse: MovieItem) {

        val intent = Intent(requireContext(), MainActivityDetail::class.java).apply {
            putExtra("MOVIE_ID", clickedHouse.id)
        }
        startActivity(intent)
    }

    private fun callApi() {
        viewModel.loadMovies()
    }

    //endregion

}