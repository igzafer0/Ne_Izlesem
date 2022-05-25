package com.igzafer.neizlesem.presentation.view_model.saved_fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.domain.usecase.movies.*
import com.igzafer.neizlesem.presentation.view_model.home_fragment.HomeFragmentViewModel

@Suppress("UNCHECKED_CAST")
class SavedPageFragmentViewModelFactory(
    private val app: Application,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SavedPageFragmentViewModel(
            app,
            getSavedMoviesUseCase,
        ) as T
    }

}