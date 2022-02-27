package com.minasmina.daggerplayground.userdetails

import androidx.lifecycle.*
import com.minasmina.daggerplayground.model.MoviesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class MovieViewModel @AssistedInject constructor(
    repository: MoviesRepository,
    @Assisted movieId: Int
) : ViewModel() {

    private val _movie = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            println("MovieViewModel(movieId=$movieId)")
            _movie.value = repository.getMovieById(movieId)
        }
    }

    val movie: LiveData<String> get() = _movie

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun factory(
            factory: Factory,
            movieId: Int
        ) : ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    factory.create(movieId) as T
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(movieId: Int): MovieViewModel
    }
}