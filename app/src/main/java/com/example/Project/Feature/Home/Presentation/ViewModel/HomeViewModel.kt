package com.example.Project.Feature.Home.Presentation.ViewModel

import androidx.lifecycle.*
import com.example.Project.GenreItem
import com.example.Project.MovieItem
import com.example.Project.shared_component.Repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel()  {


    //region Properties
    //defualt genre id from sheet
    var genreId = 9

    private val _movies = MutableLiveData<List<MovieItem>>()
    val movies: LiveData<List<MovieItem>> = _movies

    private val _genres = MutableLiveData<List<GenreItem>>()
    val genres: LiveData<List<GenreItem>> = _genres
    //endregion

    //region methods

    fun loadMovies() {
        viewModelScope.launch {
            repository.getAllMovies(genreId).onSuccess {
                _movies.value = it.data
            }.onFailure { Throwable("service not working") }

        }}

    fun loadGenres(){
        viewModelScope.launch {
            repository.getAllGenres()?.onSuccess {
                _genres.value = it.data
            }?.onFailure { Throwable("service not working") }

        }}

    //endregion


}