package com.igzafer.neizlesem.presentation.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.*
import com.igzafer.neizlesem.data.model.actor.ActorsModel
import com.igzafer.neizlesem.data.model.category.CategoryModel
import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import com.igzafer.neizlesem.domain.usecase.actors.GetPopularActorsUseCase
import com.igzafer.neizlesem.domain.usecase.categories.GetMovieCategoriesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetMovieDetailsUseCase
import com.igzafer.neizlesem.presentation.view.MovieDetailsFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.security.PrivateKey

class MovieDetailsFragmentViewModel(
    private val app: Application,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,

    ) : AndroidViewModel(app) {

    suspend fun getMovieDetails(movieId: Int): Flow<BaseMovieDetailsModel> =
        flow {
            val response = getMovieDetailsUseCase.execute(movieId).data!!
            emit(response)
        }
}