package com.igzafer.neizlesem.domain.repository

import com.igzafer.neizlesem.data.remote.models.movie.movie_credits.MovieCreditsObject
import com.igzafer.neizlesem.data.remote.models.people.CreditsDataObject
import com.igzafer.neizlesem.data.remote.models.people.PersonDetailData

interface PeopleRepository {
    suspend fun getCredits(movieId: Int): CreditsDataObject
    suspend fun getPersonDetail(personId: Int): PersonDetailData
    suspend fun getPersonMovieCredits(personId: Int):MovieCreditsObject
}