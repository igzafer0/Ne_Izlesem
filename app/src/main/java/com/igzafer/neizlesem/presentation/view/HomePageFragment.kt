package com.igzafer.neizlesem.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.igzafer.neizlesem.MainActivity
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.FragmentHomePageBinding
import com.igzafer.neizlesem.presentation.adapter.Movie.NowPlayingMoviesRowAdapter
import com.igzafer.neizlesem.presentation.adapter.Movie.PopularMoviesRowAdapter
import com.igzafer.neizlesem.presentation.adapter.Movie.TrendingWeeklyMoviesAdapter
import com.igzafer.neizlesem.presentation.adapter.Movie.UpcomingMoviesAdapter
import com.igzafer.neizlesem.presentation.view_model.home_fragment.HomeFragmentViewModel
import kotlinx.coroutines.flow.collectLatest


class HomePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    private lateinit var binding: FragmentHomePageBinding
    lateinit var viewModel: HomeFragmentViewModel
    private lateinit var nowPlayingAdapter: NowPlayingMoviesRowAdapter
    private lateinit var popularAdapter: PopularMoviesRowAdapter
    private lateinit var soonAdapter: UpcomingMoviesAdapter
    private lateinit var trendingWeeklyAdapter: TrendingWeeklyMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomePageBinding.bind(view)
        viewModel = (activity as MainActivity).homeFragmentViewModel
        nowPlayingAdapter = (activity as MainActivity).recyAdapterNowPlaying
        popularAdapter = (activity as MainActivity).recyAdapterPopular
        soonAdapter = (activity as MainActivity).recyAdapterUpcoming
        trendingWeeklyAdapter = (activity as MainActivity).recyAdapterTrendingWeekly

        initRecys()
        getDatas()
        nowPlayingAdapter.setOnClickItemListener {
            val bundle = Bundle().apply {
                putSerializable("MovieModel", it)
            }
            findNavController().navigate(
                R.id.action_homePageFragment_to_movieDetailsFragment,
                bundle
            )
        }
        popularAdapter.setOnClickItemListener {
            val bundle = Bundle().apply {
                putSerializable("MovieModel", it)
            }
            findNavController().navigate(
                R.id.action_homePageFragment_to_movieDetailsFragment,
                bundle
            )
        }
        soonAdapter.setOnClickItemListener {
            val bundle = Bundle().apply {
                putSerializable("MovieModel", it)
            }
            findNavController().navigate(
                R.id.action_homePageFragment_to_movieDetailsFragment,
                bundle
            )
        }
        trendingWeeklyAdapter.setOnClickItemListener {
            val bundle = Bundle().apply {
                putSerializable("MovieModel", it)
            }
            findNavController().navigate(
                R.id.action_homePageFragment_to_movieDetailsFragment,
                bundle
            )
        }
        nowPlayingAdapter.addOnPagesUpdatedListener {
            binding.loadingPopular.visibility = View.GONE
            binding.loadingPopular.cancelAnimation()
            binding.loadingNowPlaying.visibility = View.GONE
            binding.loadingNowPlaying.cancelAnimation()
            binding.loadingTrend.visibility = View.GONE
            binding.loadingTrend.cancelAnimation()
            binding.loadingSoon.visibility = View.GONE
            binding.loadingSoon.cancelAnimation()
        }

    }

    private fun getDatas() {
        lifecycleScope.launchWhenCreated {
            viewModel.getNowPlayingMoviesList().collectLatest {
                nowPlayingAdapter.submitData(it)

            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.getPopularMoviesList().collectLatest {
                popularAdapter.submitData(it)
            }
        }
        lifecycleScope.launchWhenCreated {
                trendingWeeklyAdapter.differ.submitList(viewModel.getTrendingWeeklyMoviesList().moviesModels)
        }
        lifecycleScope.launchWhenCreated {
            viewModel.getUpcomingMoviesList().collectLatest {
                soonAdapter.submitData(it)
            }
        }
    }

    private fun initRecys() {
        binding.soonRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = soonAdapter
        }
        binding.nowPlayingRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter
        }
        binding.trendRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingWeeklyAdapter
        }
        binding.popularRw.apply {
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
    }


}