package com.igzafer.neizlesem.presentation.view_model

import androidx.lifecycle.ViewModel
import com.igzafer.neizlesem.data.remote.models.movie.movie_credits.toMovieCreditsModel
import com.igzafer.neizlesem.data.remote.models.people.toPersonDetailModel
import com.igzafer.neizlesem.data.util.sozluk
import com.igzafer.neizlesem.domain.model.CreditsModel
import com.igzafer.neizlesem.domain.model.MovieCreditsModel
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.PersonDetailModel
import com.igzafer.neizlesem.domain.repository.MovieRepository
import com.igzafer.neizlesem.domain.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {
    private lateinit var job: String
    suspend fun getPersonDetails(personId: Int): PersonDetailModel {
        val response = repository.getPersonDetail(personId).toPersonDetailModel()
        job = response.knowForDepartment!!
        return response
    }

    suspend fun getPersonMovieCredits(personId: Int): List<MovieCreditsModel> {
        val response = repository.getPersonMovieCredits(personId)
        return if (response.crew.size > response.cast.size) {
            val returnData = mutableListOf<MovieCreditsModel>()
            for (i in response.crew.indices) {
                if (sozluk(response.crew[i].job!!) == job) {
                    returnData.add(response.crew[i].toMovieCreditsModel())
                } else if (sozluk(response.crew[i].job!!) != job && sozluk(response.crew[i].job!!) == "Yönetmen") {
                    returnData.add(response.crew[i].toMovieCreditsModel())
                }
            }
            returnData.addAll(response.cast.toMovieCreditsModel())
            returnData
        } else {
            response.cast.toMovieCreditsModel()
        }

    }
}