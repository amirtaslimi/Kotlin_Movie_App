package com.example.Project.Feature.Favorite.Presentation.ViewModel

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.example.Project.MovieItem
import com.example.Project.shared_component.Repository.MovieRepository
import com.example.Project.hilt.LikedMoviesPrefs
import com.example.Project.shared_component.data.Constants
import com.example.Project.utils.extensions.getStringList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: MovieRepository,
    @LikedMoviesPrefs private val likedMoviesSharedPreferences: SharedPreferences
) : ViewModel()  {


    //region Properties
    private val _movies = MutableLiveData<List<MovieItem>>()
    val movies: LiveData<List<MovieItem>> = _movies
    //endregion

    //region methods

    fun loadMovies() {
        viewModelScope.launch {
            repository.getAllMovies(9).onSuccess {
                val likedMovieIds = likedMoviesSharedPreferences.getStringList(Constants.SHARED_PREFERENCE_KEY_LIKED_MOVIES)
                if (likedMovieIds.isNullOrEmpty()){
                    _movies.value = null
                }
                else{
                    val filteredMovies = it.data.filter {movie -> likedMovieIds.contains(movie.id.toString()) }
                    _movies.value = filteredMovies
                }

            }.onFailure { Throwable("service not working") }
        }}

    //endregion


}
