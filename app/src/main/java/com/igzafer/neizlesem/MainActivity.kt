package com.igzafer.neizlesem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.igzafer.neizlesem.databinding.ActivityMainBinding
import com.igzafer.neizlesem.presentation.adapter.PopularMoviesAdapter
import com.igzafer.neizlesem.presentation.view_model.PopularMoviesViewModel
import com.igzafer.neizlesem.presentation.view_model.PopularMoviesViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: PopularMoviesViewModelFactory
    @Inject
    lateinit var recyAdapter: PopularMoviesAdapter
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PopularMoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this,factory)[PopularMoviesViewModel::class.java]
        initRecy()
        initData()
    }

    private fun initData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getPopularMoviesList().collectLatest {
                recyAdapter.submitData(it)
            }
        }
    }

    private fun initRecy() {
        binding.rwPopularMovie.apply {
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=recyAdapter
        }
    }
}