package com.igzafer.neizlesem.data.repository

import com.igzafer.neizlesem.data.remote.NeIzlesemApi
import com.igzafer.neizlesem.data.remote.models.movie.movie_credits.MovieCreditsObject
import com.igzafer.neizlesem.data.remote.models.people.CreditsDataObject
import com.igzafer.neizlesem.data.remote.models.people.PersonDetailData
import com.igzafer.neizlesem.domain.repository.PeopleRepository
import javax.inject.Inject

class PeopleImpl @Inject constructor(private val apiService: NeIzlesemApi) : PeopleRepository {
    override suspend fun getCredits(movieId: Int): CreditsDataObject {
        return apiService.getCast(movieId)
    }

    override suspend fun getPersonDetail(personId: Int): PersonDetailData {
        return apiService.getPersonDetails(personId)
    }

    override suspend fun getPersonMovieCredits(personId: Int): MovieCreditsObject {
        return apiService.getPersonMovieCredits(personId)
    }
}