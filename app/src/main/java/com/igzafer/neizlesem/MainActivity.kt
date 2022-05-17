package com.igzafer.neizlesem

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.igzafer.neizlesem.data.util.lightStatusBar
import com.igzafer.neizlesem.data.util.setFullScreen
import com.igzafer.neizlesem.databinding.ActivityMainBinding
import com.igzafer.neizlesem.presentation.adapter.Actors.PopularActorsRowAdapter
import com.igzafer.neizlesem.presentation.adapter.Category.MovieCategoryAdapter
import com.igzafer.neizlesem.presentation.adapter.Movie.*
import com.igzafer.neizlesem.presentation.view.MovieCategoryFragment
import com.igzafer.neizlesem.presentation.view_model.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var homeFragmentViewModelFactory: HomeFragmentViewModelFactory

    @Inject
    lateinit var movieCategoryFragmentViewModelFactory: MovieCategoryFragmentViewModelFactory

    @Inject
    lateinit var searchFragmentViewModelFactory: SearchFragmentViewModelFactory

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

    private lateinit var binding: ActivityMainBinding
    lateinit var homeFragmentViewModel: HomeFragmentViewModel
    lateinit var searchFragmentViewModel: SearchFragmentViewModel
    lateinit var movieCategoryFragmentViewModel: MovieCategoryFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

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

        searchFragmentViewModel = ViewModelProvider(
            this,
            searchFragmentViewModelFactory
        )[SearchFragmentViewModel::class.java]

        movieCategoryFragmentViewModel = ViewModelProvider(
            this,
            movieCategoryFragmentViewModelFactory
        )[MovieCategoryFragmentViewModel::class.java]


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




