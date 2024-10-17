package com.example.Project

import androidx.lifecycle.*
import com.example.Project.shared_component.Repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel@Inject constructor(
    private val repository: MovieRepository
)  : ViewModel() {
//region Properties
    private val _movie = MutableLiveData<MovieItem>()
    val movie: LiveData<MovieItem> = _movie
    //endregion

    //region methods

    fun loadMovieById(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieById(movieId).onSuccess {
                _movie.value = it.data
            }.onFailure { Throwable("service not working") }

        }}
    //endregion


}