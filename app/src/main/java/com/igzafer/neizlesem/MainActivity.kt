package com.igzafer.neizlesem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.databinding.ActivityMainBinding
import com.igzafer.neizlesem.presentation.adapter.NowPlayingMoviesRowAdapter
import com.igzafer.neizlesem.presentation.adapter.PopularMoviesRowAdapter
import com.igzafer.neizlesem.presentation.view.PopularRowFragment
import com.igzafer.neizlesem.presentation.view.VizyondakilerRowFragment
import com.igzafer.neizlesem.presentation.view_model.NowPlayingMoviesViewModel
import com.igzafer.neizlesem.presentation.view_model.NowPlayingMoviesViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.PopularMoviesViewModel
import com.igzafer.neizlesem.presentation.view_model.PopularMoviesViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var popularMoviesViewModelFactory: PopularMoviesViewModelFactory
    @Inject
    lateinit var nowPlayingMoviesViewModelFactory: NowPlayingMoviesViewModelFactory
    @Inject
    lateinit var recyAdapterNowPlaying: NowPlayingMoviesRowAdapter

    @Inject
    lateinit var recyAdapterPopular: PopularMoviesRowAdapter
    private lateinit var binding: ActivityMainBinding
    lateinit var popularMoviesViewModel: PopularMoviesViewModel
    lateinit var nowPlayingMoviesViewModel: NowPlayingMoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        popularMoviesViewModel = ViewModelProvider(this, popularMoviesViewModelFactory)[PopularMoviesViewModel::class.java]
        nowPlayingMoviesViewModel = ViewModelProvider(this, nowPlayingMoviesViewModelFactory)[NowPlayingMoviesViewModel::class.java]
        initFragment()
    }

    private fun initFragment() {
        val fragment1 = VizyondakilerRowFragment()
        val fragment2 = PopularRowFragment()
        val fragment3 = VizyondakilerRowFragment()
        val manager1 = supportFragmentManager
        val manager2 = supportFragmentManager
        val manager3 = supportFragmentManager
        manager1.beginTransaction().replace(R.id.firsFragment,fragment1,fragment1.tag).commit()
        manager2.beginTransaction().replace(R.id.secondFragment,fragment2,fragment2.tag).commit()
        manager3.beginTransaction().replace(R.id.thirdFragment,fragment3,fragment3.tag).commit()

    }


}