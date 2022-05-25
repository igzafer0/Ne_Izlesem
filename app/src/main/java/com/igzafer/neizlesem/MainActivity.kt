package com.igzafer.neizlesem

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.igzafer.neizlesem.data.util.lightStatusBar
import com.igzafer.neizlesem.data.util.setFullScreen
import com.igzafer.neizlesem.databinding.ActivityMainBinding
import com.igzafer.neizlesem.presentation.adapter.Actors.CastRowAdapter
import com.igzafer.neizlesem.presentation.adapter.Actors.PopularActorsRowAdapter
import com.igzafer.neizlesem.presentation.adapter.Category.MovieCategoryAdapter
import com.igzafer.neizlesem.presentation.adapter.Movie.*
import com.igzafer.neizlesem.presentation.view_model.category_fragment.MovieCategoryFragmentViewModel
import com.igzafer.neizlesem.presentation.view_model.category_fragment.MovieCategoryFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.details_fragment.MovieDetailsFragmentViewModel
import com.igzafer.neizlesem.presentation.view_model.details_fragment.MovieDetailsFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.home_fragment.HomeFragmentViewModel
import com.igzafer.neizlesem.presentation.view_model.home_fragment.HomeFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.saved_fragment.SavedPageFragmentViewModel
import com.igzafer.neizlesem.presentation.view_model.saved_fragment.SavedPageFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.search_fragment.SearchFragmentViewModel
import com.igzafer.neizlesem.presentation.view_model.search_fragment.SearchFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.search_fragment.SearchPageFragmentViewModel
import com.igzafer.neizlesem.presentation.view_model.search_fragment.SearchPageFragmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var homeFragmentViewModelFactory: HomeFragmentViewModelFactory

    @Inject
    lateinit var searchFragmentViewModelFactory: SearchFragmentViewModelFactory

    @Inject
    lateinit var movieDetailsFragmentViewModelFactory: MovieDetailsFragmentViewModelFactory

    @Inject
    lateinit var movieCategoryFragmentViewModelFactory: MovieCategoryFragmentViewModelFactory

    @Inject
    lateinit var searchPageFragmentViewModelFactory: SearchPageFragmentViewModelFactory

    @Inject
    lateinit var savedPageFragmentViewModelFactory: SavedPageFragmentViewModelFactory

    @Inject
    lateinit var recyAdapterNowPlaying: NowPlayingMoviesRowAdapter

    @Inject
    lateinit var recyAdapterPopular: PopularMoviesRowAdapter

    @Inject
    lateinit var recyAdapterUpcoming: UpcomingMoviesAdapter

    @Inject
    lateinit var recyAdapterTrendingWeekly: TrendingWeeklyMoviesAdapter

    @Inject
    lateinit var recyAdapterPopularActors: PopularActorsRowAdapter

    @Inject
    lateinit var recyAdapterMovieCategoryAdapter: MovieCategoryAdapter

    @Inject
    lateinit var recyAdapterDiscoverMoviesAdapter: DiscoverMoviesAdapter

    @Inject
    lateinit var recyAdapterSearchMovieAdapter: SearchMovieAdapter

    @Inject
    lateinit var recyAdapterGetCast: CastRowAdapter

    @Inject
    lateinit var recyAdapterSavedMoviesAdapter: SavedMoviesAdapter

    private lateinit var binding: ActivityMainBinding
    lateinit var homeFragmentViewModel: HomeFragmentViewModel
    lateinit var searchPageFragmentViewModel: SearchPageFragmentViewModel
    lateinit var movieCategoryFragmentViewModel: MovieCategoryFragmentViewModel
    lateinit var movieDetailsFragmentViewModel: MovieDetailsFragmentViewModel
    lateinit var searchFragmentViewModel: SearchFragmentViewModel
    lateinit var savedPageFragmentViewModel: SavedPageFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        viewModels()

        setFullScreen(window)
        lightStatusBar(window)
        initFragment()


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

    }

    private fun viewModels() {
        homeFragmentViewModel = ViewModelProvider(
            this,
            homeFragmentViewModelFactory
        )[HomeFragmentViewModel::class.java]

        searchPageFragmentViewModel = ViewModelProvider(
            this,
            searchPageFragmentViewModelFactory
        )[SearchPageFragmentViewModel::class.java]

        movieCategoryFragmentViewModel = ViewModelProvider(
            this,
            movieCategoryFragmentViewModelFactory
        )[MovieCategoryFragmentViewModel::class.java]

        movieDetailsFragmentViewModel = ViewModelProvider(
            this,
            movieDetailsFragmentViewModelFactory
        )[MovieDetailsFragmentViewModel::class.java]

        searchFragmentViewModel = ViewModelProvider(
            this,
            searchFragmentViewModelFactory
        )[SearchFragmentViewModel::class.java]

        savedPageFragmentViewModel = ViewModelProvider(
            this,
            savedPageFragmentViewModelFactory
        )[SavedPageFragmentViewModel::class.java]

    }

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private fun initFragment() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.main_nav)
        navHostFragment = (supportFragmentManager
            .findFragmentById(R.id.viewPager) as NavHostFragment?)!!
        navController = navHostFragment.navController
        val menu = popupMenu.menu
        binding.tabLayout.setupWithNavController(menu, navController)

    }


}




