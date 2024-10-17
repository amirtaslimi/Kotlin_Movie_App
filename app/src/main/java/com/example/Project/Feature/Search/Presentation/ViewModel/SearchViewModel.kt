package com.example.Project.Feature.Search.Presentation.ViewModel

import androidx.lifecycle.*
import com.example.Project.MovieItem
import com.example.Project.shared_component.Repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel()  {



    //region Properties
    private val _movies = MutableLiveData<List<MovieItem>>()
    val movies: LiveData<List<MovieItem>> = _movies

    private val _filteredMovies = MutableLiveData<List<MovieItem>>()
    val filteredMovies: LiveData<List<MovieItem>> = _filteredMovies
    //endregion


    //region methods

    fun loadMovies() {
        viewModelScope.launch {
            repository.getAllMovies(9).onSuccess {
                _movies.value = it.data
                _filteredMovies.value = it.data
            }.onFailure { Throwable("service not working") }
        }}

    fun filterMovies(query: String) {
        val filteredList = _movies.value?.filter { movie ->
            movie.title.contains(query, ignoreCase = true)
        }
        _filteredMovies.value = filteredList
    }

    //endregion


}
