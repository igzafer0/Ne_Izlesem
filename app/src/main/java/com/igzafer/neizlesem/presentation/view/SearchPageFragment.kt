package com.igzafer.neizlesem.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.igzafer.neizlesem.MainActivity
import com.igzafer.neizlesem.R

import com.igzafer.neizlesem.databinding.FragmentSearchPageBinding
import com.igzafer.neizlesem.presentation.adapter.Actors.PopularActorsRowAdapter
import com.igzafer.neizlesem.presentation.adapter.Category.MovieCategoryAdapter
import com.igzafer.neizlesem.presentation.view_model.search_fragment.SearchPageFragmentViewModel
import kotlinx.coroutines.flow.collectLatest


class SearchPageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_page, container, false)
    }

    private lateinit var binding: FragmentSearchPageBinding
    lateinit var viewModelPage: SearchPageFragmentViewModel
    private lateinit var popularActorsAdapter: PopularActorsRowAdapter
    private lateinit var movieCategoryAdapter: MovieCategoryAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchPageBinding.bind(view)
        viewModelPage = (activity as MainActivity).searchPageFragmentViewModel
        popularActorsAdapter = (activity as MainActivity).recyAdapterPopularActors
        movieCategoryAdapter = (activity as MainActivity).recyAdapterMovieCategoryAdapter
        getDatas()
        initRecys()
        binding.searchPageButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchPageFragment_to_searchFragment)
        }
        movieCategoryAdapter.setOnClickItemListener {
            Log.d("winter", it.name)
            val bundle = Bundle().apply {
                putSerializable("CategoryModel", it)
            }
            findNavController().navigate(
                R.id.action_searchPageFragment_to_movieCategoryFragment,
                bundle
            )
        }

    }

    private fun getDatas() {
        lifecycleScope.launchWhenCreated {
            viewModelPage.getPopularActors().collectLatest {
                popularActorsAdapter.submitData(it)
            }
        }
        lifecycleScope.launchWhenCreated {

            movieCategoryAdapter.differ.submitList(viewModelPage.getMovieCategories().categoryModels)

        }
    }

    private fun initRecys() {
        binding.popularActorRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularActorsAdapter
        }
        binding.movieCategoryRw.apply {
            layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.HORIZONTAL)
            adapter = movieCategoryAdapter
        }
    }
}